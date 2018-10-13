package com.dawson.aaaccount.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.dawson.aaaccount.utils.CommonUtils;

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
		List<Daybook> daybooks = getUnSettledDaybook(settle.getFamily());
		Set<Daybook> sDaybooks = new HashSet<>();
		sDaybooks.addAll(daybooks);
		settle.setDaybooks(sDaybooks);
		settle = settleRepository.save(settle);

		if (settle == null)
			return new OperateResult<>();
		else
			return new OperateResult<>(settle.getId());
	}

	@Override
	public OperateResult<List<Settle>> getFamilySettle(String fid) {
		final Family family = new Family(fid);
		Specification<Settle> specification = new Specification<Settle>() {
			@Override
			public Predicate toPredicate(Root<Settle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.and(cb.equal(root.get("family").as(Family.class), family));
				query.where(predicate).orderBy(cb.desc(root.get("date").as(Date.class)));
				return null;
			}
		};
		List<Settle> settles = settleRepository.findAll(specification);
		if (settles != null && !settles.isEmpty()) {
			settles.forEach(new Consumer<Settle>() {

				@Override
				public void accept(Settle ts) {
					ts.setCreator(ts.getCreator().cleanClone());
					ts.setDaybooks(new HashSet<>());
					ts.setFamily(ts.getFamily().cleanClone());
					ts.getDetails().forEach(new Consumer<SettleDetail>() {

						@Override
						public void accept(SettleDetail tsd) {
							User tUser = tsd.getUser().cleanClone();
							tUser.setName(tsd.getUser().getName());
							tsd.setUser(tUser);
							tsd.setSettle(tsd.getSettle().cleanClone());
						}
					});
				}
			});
		}
		return new OperateResult<List<Settle>>(settles);
	}

	@Override
	public OperateResult<Settle> statistic(String fid, Date start, Date end) {
		final Family family = new Family(fid);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(end);
		calendar.add(Calendar.DATE, 1);
		Specification<Daybook> specification = new Specification<Daybook>() {
			@Override
			public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.and(cb.equal(root.get("family").as(Family.class), family),
						cb.between(root.get("date").as(Date.class), cb.literal(start), cb.literal(calendar.getTime())));
				query.where(predicate).orderBy(cb.desc(root.get("date").as(Date.class)));
				return null;
			}
		};

		List<Daybook> daybooks = daybookRepository.findAll(specification);
		if (daybooks == null || daybooks.isEmpty()) {
			Settle settle = new Settle(null);
			settle.setFamily(family);
			settle.setStartDate(start);
			settle.setEndDate(end);
			return new OperateResult<Settle>(settle);
		} else
			return handleDaybook(family, daybooks);
	};

	@Override
	public OperateResult<Settle> statisticUnSettled(String fid) {

		final Family family = new Family(fid);
		List<Daybook> daybooks = getUnSettledDaybook(family);
		if (daybooks == null || daybooks.isEmpty())
			return new OperateResult<Settle>(null);
		else
			return handleDaybook(family, daybooks);
	}

	private List<Daybook> getUnSettledDaybook(final Family family) {
		Specification<Daybook> specification = new Specification<Daybook>() {
			@Override
			public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicate = cb.and(cb.equal(root.get("family").as(Family.class), family),
						cb.isNull(root.get("settle").as(Settle.class)));
				query.where(predicate).orderBy(cb.desc(root.get("date").as(Date.class)));
				return null;
			}
		};

		return daybookRepository.findAll(specification);
	}

	private OperateResult<Settle> handleDaybook(Family family, List<Daybook> daybooks) {
		Settle settle = new Settle(null);
		settle.setFamily(family);
		settle.setStartDate(daybooks.get(0).getDate());
		settle.setEndDate(daybooks.get(daybooks.size() - 1).getDate());
		Set<SettleDetail> settleDetails = new HashSet<SettleDetail>();
		Family family2 = familyRepository.findById(family.getId()).get();
		family2.getMember().forEach(new Consumer<User>() {
			@Override
			public void accept(User user) {
				SettleDetail settleDetail = new SettleDetail(null);
				User user2 = user.cleanClone();
				user2.setName(user.getName());
				settleDetail.setUser(user2);
				settleDetail.setPay(new BigDecimal(0.0));
				settleDetail.setConsume(new BigDecimal(0.0));
				settleDetails.add(settleDetail);
			}
		});

		BigDecimal totalMoney = new BigDecimal(0.0);
		for (int i = 0; i < daybooks.size(); i++) {
			Daybook dbook = daybooks.get(i);

			BigDecimal m = dbook.getMoney();// 消费金额
			totalMoney = totalMoney.add(m);// 消费总金额
			List<User> cms = dbook.getConsumer();// 消费人员
			BigDecimal avm = m.divide(new BigDecimal(cms.size()));// 平均消费金额
			// 付款金额
			User puser = dbook.getPayer();
			settleDetails.forEach(new Consumer<SettleDetail>() {
				@Override
				public void accept(SettleDetail detail) {
					if (CommonUtils.userEquals(puser, detail.getUser()))
						detail.setPay(detail.getPay().add(m));
				}
			});
			// 消费金额
			cms.forEach(new Consumer<User>() {
				@Override
				public void accept(User user) {
					settleDetails.forEach(new Consumer<SettleDetail>() {
						@Override
						public void accept(SettleDetail detail) {
							if (CommonUtils.userEquals(user, detail.getUser()))
								detail.setConsume(detail.getConsume().add(avm));
						}
					});
				}
			});
		}

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
	}

	@Override
	public OperateResult<Settle> statisticMine(String uid, Date start, Date end) {

		User user = new User();
		user.setId(uid);

		Specification<Daybook> specification = new Specification<Daybook>() {
			@Override
			public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicate = cb.and(cb.equal(root.get("recorder").as(User.class), user),
						root.get("family").isNull(),
						cb.between(root.get("date").as(Date.class), cb.literal(start), cb.literal(end)));
				query.where(predicate).orderBy(cb.desc(root.get("createTime").as(Date.class)));
				return null;
			}
		};

		List<Daybook> daybooks = daybookRepository.findAll(specification);
		Settle settle = new Settle(null);

		if (daybooks == null || daybooks.isEmpty()) {
			settle.setStartDate(start);
			settle.setEndDate(end);
			return new OperateResult<Settle>(settle);
		}

		settle.setStartDate(daybooks.get(0).getDate());
		settle.setEndDate(daybooks.get(daybooks.size() - 1).getDate());
		BigDecimal totalMoney = new BigDecimal(0.0);

		for (int i = 0; i < daybooks.size(); i++) {
			totalMoney = totalMoney.add(daybooks.get(i).getMoney());// 消费总金额
		}

		settle.setMoney(totalMoney);

		return new OperateResult<Settle>(settle);

	}
}
