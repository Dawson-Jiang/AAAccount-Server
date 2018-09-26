package com.dawson.aaaccount.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service; 

import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.Family; 
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.Settle;
import com.dawson.aaaccount.entity.SettleDetail;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.repository.DaybookRepository;
import com.dawson.aaaccount.repository.FamilyRepository;
import com.dawson.aaaccount.repository.SettleRepository;
import com.dawson.aaaccount.repository.UserRepository;

@Service("settleService")
public class SettleServiceImpl implements SettleService {

	@Resource
	private SettleRepository settleRepository;

	@Resource
	private DaybookRepository daybookRepository;

	@Resource
	private FamilyRepository familyRepository;

	@Resource
	private UserRepository userRepository;

	@Override
	public OperateResult<String> settle(Settle settle) {
		settleRepository.save(settle);
		return null;
	}

	@Override
	public OperateResult<List<Settle>> getFamilySettle(Map<String, String> param) {

		return null;
	}

	@Override
	public OperateResult<Settle> statistic(Map<String, String> param) {
		final Family family = new Family(true);
		family.setId(param.get("fid"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Specification<Daybook> specification = new Specification<Daybook>() {
			@Override
			public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Date start = null, end = null;
				try {
					start = simpleDateFormat.parse(param.get("start"));
					end = simpleDateFormat.parse(param.get("end"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Predicate predicate = cb.and(cb.equal(root.get("family").as(Family.class), family),
						cb.between(root.get("date").as(Date.class), cb.literal(start), cb.literal(end)),
						cb.notEqual(root.get("settled"), 1));
				query.where(predicate).orderBy(cb.desc(root.get("createTime").as(Date.class)));
				return null;
			}
		};

		List<Daybook> daybooks = daybookRepository.findAll(specification);
		Settle settle = new Settle(true);
		settle.setFamily(family);
		settle.setStartDate(daybooks.get(0).getDate());
		settle.setEndDate(daybooks.get(daybooks.size() - 1).getDate());
		if (daybooks.isEmpty()) {
			return new OperateResult<Settle>(settle);
		}

		List<SettleDetail> settleDetails = new ArrayList<SettleDetail>();
		Family family2 = familyRepository.findById(family.getId()).get();
		family2.getMember().forEach(new Consumer<User>() {

			@Override
			public void accept(User user) {

				SettleDetail settleDetail = new SettleDetail();
				settleDetail.setUser(user);
				settleDetails.add(settleDetail);
			}
		});

		BigDecimal totalMoney = new BigDecimal(0.0);
		daybooks.forEach(new Consumer<Daybook>() {
			@Override
			public void accept(Daybook dbook) {
				BigDecimal m = dbook.getMoney();// 消费金额
				totalMoney.add(m);// 消费总金额
				List<User> cms = dbook.getConsumer();// 消费人员
				BigDecimal avm = m.divide(new BigDecimal(cms.size()));// 平均消费金额
				// 付款金额
				User puser = dbook.getPayer();
				settleDetails.forEach(new Consumer<SettleDetail>() {
					@Override
					public void accept(SettleDetail detail) {
						if (userEquals(puser, detail.getUser()))
							detail.getPay().add(m);
					}
				});
				// 消费金额
				cms.forEach(new Consumer<User>() {
					@Override
					public void accept(User user) {
						settleDetails.forEach(new Consumer<SettleDetail>() {
							@Override
							public void accept(SettleDetail detail) {
								if (userEquals(user, detail.getUser()))
									detail.getConsume().add(avm);
							}
						});
					}
				});
			}
		});
		settle.setMoney(totalMoney);
		// 结算金额
		settleDetails.forEach(new Consumer<SettleDetail>() {
			@Override
			public void accept(SettleDetail detail) {
				detail.setSettleMoney(detail.getPay().add(detail.getConsume().negate()));
			}
		});
		settle.setDetails(settleDetails);
		return new OperateResult<Settle>(settle);
	};

	private boolean userEquals(User user1, User user2) {
		if (user1 == null && user2 == null)
			return true;
		if (user1 != null && user2 != null) {
			return stringEquals(user1.getId(), user2.getId());
		} else
			return false;
	}

	private boolean stringEquals(String str1, String str2) {
		if (str1 == null && str2 == null)
			return true;
		if (str1 != null && str2 != null) {
			return str1.equals(str2);
		} else
			return false;
	}

	@Override
	public OperateResult<Settle> statisticMine(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}
}
