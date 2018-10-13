package com.dawson.aaaccount.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;
import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.repository.CategoryRepository;
import com.dawson.aaaccount.repository.DaybookRepository;
import com.dawson.aaaccount.repository.FamilyRepository;
import com.dawson.aaaccount.repository.SettleRepository;
import com.dawson.aaaccount.repository.UserRepository;
import com.dawson.aaaccount.utils.LeancloudField;

@Service("commonService")
public class CommonServiceImpl implements CommonService {
	@Resource
	private UserRepository userRepository;

	@Resource
	private SettleRepository settleRepository;

	@Resource
	private DaybookRepository daybookRepository;

	@Resource
	private FamilyRepository familyRepository;

	@Resource
	private CategoryRepository categoryRepository;

	@Override
	public OperateResult<String> syncUserFromLeancloud() {
		AVQuery<AVUser> query = new AVQuery<AVUser>(LeancloudField.USER.CLASS_NAME);
		query.limit(10);// 最多返回 10 条结果
		query.skip(0 * 10);// 跳过 20 条结果
		query.orderByDescending("updatedAt");
		List<AVUser> avUsers;
		try {
			avUsers = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return new OperateResult<String>(0, e.getMessage());
		}
		List<User> users = new ArrayList<User>();
		avUsers.forEach(new Consumer<AVUser>() {

			@Override
			public void accept(AVUser avUser) {
				User duser = new User();
				duser.setId(avUser.getObjectId());
				duser.setCreateTime(avUser.getCreatedAt());
				duser.setUpdateTime(avUser.getUpdatedAt());
				duser.setName(avUser.getUsername());
				duser.setPhone(avUser.getMobilePhoneNumber());
				String adata = avUser.getJSONObject(LeancloudField.USER.AUTH_DATA).toString();
				JSONObject jsonObject = JSONObject.parseObject(adata);
				jsonObject = jsonObject.getJSONObject("qq");
				duser.setOpenid(jsonObject.getString("openid"));
				duser.setAuthData(adata);
				users.add(duser);
			}
		});

		userRepository.saveAll(users);
		return new OperateResult<String>("成功");
	}

	@Override
	public OperateResult<String> syncCategoryFromLeancloud() {
		AVQuery<AVObject> query = new AVQuery<AVObject>(LeancloudField.CONSUME_CATEGORY.CLASS_NAME);

		List<AVObject> avObjects;
		try {
			avObjects = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return new OperateResult<String>(0, e.getMessage());
		}
		List<Category> categories = new ArrayList<Category>();
		avObjects.forEach(new Consumer<AVObject>() {

			@Override
			public void accept(AVObject avObject) {
				Category category = new Category();
				category.setId(avObject.getObjectId());
				category.setCreateTime(avObject.getCreatedAt());
				category.setUpdateTime(avObject.getUpdatedAt());
				category.setName(avObject.getString(LeancloudField.CONSUME_CATEGORY.NAME));
				category.setOrderFlag(Integer.parseInt(avObject.getString(LeancloudField.CONSUME_CATEGORY.SORT_FLAG)));

				categories.add(category);
			}
		});

		categoryRepository.saveAll(categories);
		return new OperateResult<String>("成功");
	}

	@Override
	public OperateResult<String> syncDaybookFromLeancloud() {
		AVQuery<AVObject> query = new AVQuery<AVObject>(LeancloudField.DAY_BOOK.CLASS_NAME);
		query.limit(10);// 最多返回 10 条结果
		query.skip(0 * 10);// 跳过 20 条结果
		query.orderByDescending("updatedAt");
		List<AVObject> avObjects;
		try {
			avObjects = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return new OperateResult<String>(0, e.getMessage());
		}
		List<Daybook> daybooks = new ArrayList<Daybook>();
		avObjects.forEach(new Consumer<AVObject>() {

			@Override
			public void accept(AVObject avObject) {
				Daybook ddaybook = new Daybook();
				ddaybook.setId(avObject.getObjectId());
				ddaybook.setCreateTime(avObject.getCreatedAt());
				ddaybook.setUpdateTime(avObject.getUpdatedAt());
				ddaybook.setMoney(BigDecimal.valueOf(avObject.getDouble(LeancloudField.DAY_BOOK.MONEY)));
				ddaybook.setDate(avObject.getDate(LeancloudField.DAY_BOOK.DATE));
				ddaybook.setDes(avObject.getString(LeancloudField.DAY_BOOK.DESCRIPTION));

				AVObject avcategory = avObject.getAVObject(LeancloudField.DAY_BOOK.CONSUME_CATEGORY);
				Category category = new Category(avcategory.getObjectId());
 				ddaybook.setCategory(category);

 				AVUser avRecorder = avObject.getAVUser(LeancloudField.DAY_BOOK.RECORDER, AVUser.class);
				User recorder = new User(avRecorder.getObjectId());
				ddaybook.setRecorder(recorder);

				AVObject avfamily = avObject.getAVObject(LeancloudField.DAY_BOOK.FAMILY);
				boolean isTemp = false;
				if (avfamily != null) {
					Family family = new Family(avfamily.getObjectId());
 					ddaybook.setFamily(family);
					isTemp = avfamily.getBoolean(LeancloudField.FAMILY.TEMP);
				}

				User payer ;
				if (ddaybook.getFamily() != null && isTemp) {
					AVObject avpayer = avObject.getAVObject(LeancloudField.DAY_BOOK.PAYER2);
					payer = new User(avpayer.getObjectId());
				} else {
					AVUser avpayer = avObject.getAVUser(LeancloudField.DAY_BOOK.PAYER, AVUser.class);
					payer = new User(avpayer.getObjectId());
				}
				ddaybook.setPayer(payer);

				final List<User> consumer = new ArrayList<>();
				if (ddaybook.getFamily() != null) {
					if (isTemp) {
						AVRelation<AVObject> mrelation = avObject.getRelation(LeancloudField.DAY_BOOK.CONSUMER2);
						AVQuery<AVObject> mquery = mrelation.getQuery();
						List<AVObject> avmembers;
						try {
							avmembers = mquery.find();
							avmembers.forEach(new Consumer<AVObject>() {

								@Override
								public void accept(AVObject member) { 
									consumer.add(new User(member.getObjectId()));
								}
							});
						} catch (AVException e) {
							e.printStackTrace();
						}
					} else {
						AVRelation<AVUser> urelation = avObject.getRelation(LeancloudField.DAY_BOOK.CONSUMER);
						AVQuery<AVUser> uquery = urelation.getQuery();

						List<AVUser> avumembers;
						try {
							avumembers = uquery.find();
							avumembers.forEach(new Consumer<AVUser>() {

								@Override
								public void accept(AVUser umember) { 
									consumer.add(new User(umember.getObjectId()));
								}
							});

						} catch (AVException e) {
							e.printStackTrace();
						}
					}
					ddaybook.setConsumer(consumer);
				}

				
				String files = avObject.getString(LeancloudField.DAY_BOOK.PICTURES);
				String tfiles = avObject.getString(LeancloudField.DAY_BOOK.THUM_PICTURES);
				if (!TextUtils.isEmpty(files)) {
					String[] fs = files.split(";");
					String[] tfs = tfiles.split(";");
					ddaybook.setPic1(fs[0] + ";" + tfs[0]);
					if (fs.length > 1)
						ddaybook.setPic2(fs[1] + ";" + tfs[1]);
					if (fs.length > 2)
						ddaybook.setPic3(fs[2] + ";" + tfs[2]);
				}
				daybooks.add(ddaybook);
			}
		});

		daybookRepository.saveAll(daybooks);
		return new OperateResult<String>("成功");
	}

	@Override
	public OperateResult<String> syncMemberFromLeancloud() {
		AVQuery<AVObject> query = new AVQuery<AVObject>(LeancloudField.MEMBER.CLASS_NAME);
		query.limit(10);// 最多返回 10 条结果
		query.skip(0 * 10);// 跳过 20 条结果
		query.orderByDescending("updatedAt");
		List<AVObject> avObjects;
		try {
			avObjects = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return new OperateResult<String>(0, e.getMessage());
		}
		List<User> users = new ArrayList<User>();
		avObjects.forEach(new Consumer<AVObject>() {

			@Override
			public void accept(AVObject avObject) {
				User duser = new User();
				duser.setId(avObject.getObjectId());
				duser.setCreateTime(avObject.getCreatedAt());
				duser.setUpdateTime(avObject.getUpdatedAt());
				duser.setName(avObject.getString(LeancloudField.MEMBER.NAME));
				duser.setMember(true);
				users.add(duser);
			}
		});

		userRepository.saveAll(users);
		return new OperateResult<String>("成功");
	}

	@Override
	public OperateResult<String> syncFamilyFromLeancloud() {
		AVQuery<AVObject> query = new AVQuery<AVObject>(LeancloudField.FAMILY.CLASS_NAME);
		query.limit(10);// 最多返回 10 条结果
		query.skip(0 * 10);// 跳过 20 条结果
		query.orderByDescending("updatedAt");
		List<AVObject> avObjects;
		try {
			avObjects = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return new OperateResult<String>(0, e.getMessage());
		}
		List<Family> families = new ArrayList<Family>();
		avObjects.forEach(new Consumer<AVObject>() {

			@Override
			public void accept(AVObject avObject) {
				Family dfamily = new Family();
				dfamily.setId(avObject.getObjectId());
				dfamily.setCreateTime(avObject.getCreatedAt());
				dfamily.setUpdateTime(avObject.getUpdatedAt());
				dfamily.setName(avObject.getString(LeancloudField.FAMILY.NAME));

				AVUser avCreator = avObject.getAVUser(LeancloudField.FAMILY.CREATOR, AVUser.class);
 				User creator = new User(avCreator.getObjectId());
 				dfamily.setCreator(creator);

				final List<User> members = new ArrayList<>();
				boolean isTemp = avObject.getBoolean(LeancloudField.FAMILY.TEMP);

				if (isTemp) {
					AVRelation<AVObject> mrelation = avObject.getRelation(LeancloudField.FAMILY.MEMBER2);
					AVQuery<AVObject> mquery = mrelation.getQuery();
					List<AVObject> avmembers;
					try {
						avmembers = mquery.find();
						avmembers.forEach(new Consumer<AVObject>() {

							@Override
							public void accept(AVObject member) {
 								members.add(new User(member.getObjectId()));
							}
						});
					} catch (AVException e) {
						e.printStackTrace();
					}

				} else {
					AVRelation<AVUser> urelation = avObject.getRelation(LeancloudField.FAMILY.MEMBER);
					AVQuery<AVUser> uquery = urelation.getQuery();

					List<AVUser> avumembers;
					try {
						avumembers = uquery.find();
						avumembers.forEach(new Consumer<AVUser>() {

							@Override
							public void accept(AVUser umember) { 
								members.add( new User(umember.getObjectId()));
							}
						});

					} catch (AVException e) {
						e.printStackTrace();
					}
				}
				dfamily.setMember(members);
				AVFile file = avObject.getAVFile(LeancloudField.FAMILY.HEAD);
				if (file != null)
					dfamily.setHead(file.getUrl());
				families.add(dfamily);
			}
		});

		familyRepository.saveAll(families);
		return new OperateResult<String>("成功");
	}

}
