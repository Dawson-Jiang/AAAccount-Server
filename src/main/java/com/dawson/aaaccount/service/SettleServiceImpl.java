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
	settle=	settleRepository.save(settle);
	if(settle==null)return new OperateResult<>();
	else  return new OperateResult<>(settle.getId());
	}

	@Override
	public OperateResult<List<Settle>> getFamilySettle(String fid) {
		return null;
	}

	@Override
	public OperateResult<Settle> statistic(String fid, Date start, Date end) {
		final Family family = new Family(true);
		family.setId(fid);
		Specification<Daybook> specification = new Specification<Daybook>() {
			@Override
			public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

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
						if (CommonUtils. userEquals(puser, detail.getUser()))
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
								if (CommonUtils. userEquals(user, detail.getUser()))
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

	@Override
	public OperateResult<Settle> statisticMine(String uid, Date start, Date end) {

		User user = new User();
		user.setId(uid);

		Specification<Daybook> specification = new Specification<Daybook>() {
			@Override
			public Predicate toPredicate(Root<Daybook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicate = cb.and(cb.equal(root.get("recorder").as(User.class), user),
						root.get("family").isNull(),
						cb.between(root.get("date").as(Date.class), cb.literal(start), cb.literal(end)),
						cb.notEqual(root.get("settled"), 1));
				query.where(predicate).orderBy(cb.desc(root.get("createTime").as(Date.class)));
				return null;
			}
		};

		List<Daybook> daybooks = daybookRepository.findAll(specification);
		Settle settle = new Settle(true);

		settle.setStartDate(daybooks.get(0).getDate());
		settle.setEndDate(daybooks.get(daybooks.size() - 1).getDate());
		if (daybooks.isEmpty()) {
			return new OperateResult<Settle>(settle);
		}

		BigDecimal totalMoney = new BigDecimal(0.0);
		daybooks.forEach(new Consumer<Daybook>() {
			@Override
			public void accept(Daybook dbook) {
				totalMoney.add(dbook.getMoney());// 消费总金额

			}
		});
		settle.setMoney(totalMoney);

		return new OperateResult<Settle>(settle);

	}
}
