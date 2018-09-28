package com.dawson.aaaccount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.Settle;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.repository.CategoryRepository;
import com.dawson.aaaccount.repository.DaybookRepository;

@Service("daybookService")
public class DaybookServiceImpl implements DaybookService {
	@Resource
	private DaybookRepository daybookRepository;

	@Override
	public OperateResult<String> save(Daybook daybook) {
		daybook.setUpdateTime(new Date());
		int res = daybookRepository.save(daybook) == null ? 0 : 1;
		if (res > 0)
			return new OperateResult<String>(daybook.getId());
		else
			return new OperateResult<String>(0, "添加失败");
	}

	@Resource
	private CategoryRepository categoryRepository;

	@Override
	public OperateResult<List<Category>> getCategory() {
		try {
			List<Category> res=(List<Category>) categoryRepository.findAll();
			if(res==null)res=new ArrayList<>();
			 return new OperateResult<>(res);
		} catch (Exception e) {
			return new OperateResult<>(0, "操作失败");
		}
	}

	@Override
	public OperateResult<Daybook> get(String id) {
		try {
			Daybook daybook = daybookRepository.findById(id).get();
			User tUser =null;
			if(daybook.getPayer()!=null) {
			  tUser = new User(true);
			tUser.setId(daybook.getPayer().getId());
			tUser.setName(daybook.getPayer().getName());
			daybook.setPayer(tUser);
			}

			tUser = new User(true);
			tUser.setId(daybook.getRecorder().getId());
			tUser.setName(daybook.getRecorder().getName());
			daybook.setRecorder(tUser);

			daybook.getCategory().setCreateTime(null);
			daybook.getCategory().setUpdateTime(null);

			List<User> tUsers = new ArrayList<>();
			daybook.getConsumer().forEach(new Consumer<User>() {

				@Override
				public void accept(User user) {
					User tUser = new User(true);
					tUser.setId(user.getId());
					tUser.setName(user.getName());
					tUsers.add(tUser);
				}
			});
			daybook.setConsumer(tUsers);

			if(daybook.getFamily()!=null) {
			Family tFamily = new Family(true);
			tFamily.setId(daybook.getFamily().getId());
			tFamily.setName(daybook.getFamily().getName());
			daybook.setFamily(tFamily);
			}

			if (daybook.getSettle() != null) {
				Settle tSettle = new Settle(true);
				tSettle.setId(daybook.getSettle().getId());
				daybook.setSettle(tSettle);
			}

			return new OperateResult<Daybook>(daybook);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResult<>(0, "查询失败");
		}
	}

	@Override
	public OperateResult<List<Daybook>> getFamilyDaybook(String fid, int page, int limit) {
		try {
			Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "date");
			Specification<Daybook> specification = new Specification<Daybook>() {
				@Override
				public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query,
						CriteriaBuilder criteriaBuilder) {
					Family family = new Family();
					family.setId(fid);
					return criteriaBuilder.and(criteriaBuilder.equal(root.get("family").as(Family.class), family));
				}
			};
			List<Daybook> res = daybookRepository.findAll(specification, pageable).getContent();
			List<Daybook> tDaybooks = new ArrayList<>();

			res.forEach(new Consumer<Daybook>() {

				@Override
				public void accept(Daybook daybook) {
					Daybook tDaybook = new Daybook(true);
					tDaybook.setId(daybook.getId());
					tDaybook.setPic1(daybook.getPic1());
					tDaybook.setMoney(daybook.getMoney());
					tDaybook.setCategory(daybook.getCategory());
					tDaybook.getCategory().setCreateTime(null);
					tDaybook.getCategory().setUpdateTime(null);
					tDaybook.setDate(daybook.getDate());

					User tUser = new User(true);
					tUser.setId(daybook.getPayer().getId());
					tUser.setName(daybook.getPayer().getName());
					tDaybook.setPayer(tUser);

					tDaybooks.add(tDaybook);
				}
			});

			return new OperateResult<List<Daybook>>(tDaybooks);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResult<>(0, "查询失败");
		}
	}

	@Override
	public OperateResult<List<Daybook>> getMyDaybook(String uid, int page, int limit) {
		try {
			Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "date");
			Specification<Daybook> specification = new Specification<Daybook>() {
				@Override
				public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query,
						CriteriaBuilder criteriaBuilder) {
					User user = new User();
					user.setId(uid);
					return criteriaBuilder.and(criteriaBuilder.equal(root.get("recorder").as(User.class), user),
							                    root.get("family").isNull());
				}
			};
			List<Daybook> res = daybookRepository.findAll(specification, pageable).getContent();
			List<Daybook> tDaybooks = new ArrayList<>();

			res.forEach(new Consumer<Daybook>() {

				@Override
				public void accept(Daybook daybook) {
					Daybook tDaybook = new Daybook(true);
					tDaybook.setId(daybook.getId());
					tDaybook.setPic1(daybook.getPic1());
					tDaybook.setMoney(daybook.getMoney());
					tDaybook.setCategory(daybook.getCategory());
					tDaybook.getCategory().setCreateTime(null);
					tDaybook.getCategory().setUpdateTime(null);
					tDaybook.setDate(daybook.getDate());

					tDaybooks.add(tDaybook);
				}
			});

			return new OperateResult<List<Daybook>>(tDaybooks);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResult<>(0, "查询失败");
		}
	}

	@Override
	public OperateResult<Object> del(String id) {
		try {
			daybookRepository.deleteById(id);
			return new OperateResult<Object>(1);
		} catch (Exception e) {
			return new OperateResult<Object>(0, e.getMessage());
		}
	}
}
