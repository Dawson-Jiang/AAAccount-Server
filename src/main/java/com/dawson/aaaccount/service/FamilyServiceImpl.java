package com.dawson.aaaccount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.http.util.TextUtils;
import org.aspectj.weaver.ast.Literal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.repository.FamilyRepository;
import com.dawson.aaaccount.repository.UserRepository;

@Service("familyService")
public class FamilyServiceImpl implements FamilyService {

	@Resource
	private FamilyRepository familyRepository;

	@Resource
	private UserRepository userRepository;

	@Override
	public OperateResult<Family> get(String id) {
		Family res = familyRepository.findById(id).get();
		List<User> users = new ArrayList<>();
		res.getMember().forEach(new Consumer<User>() {

			@Override
			public void accept(User user) {
				User tUser = new User();
				tUser.setId(user.getId());
				tUser.setName(user.getName());
				tUser.setMember(user.isMember());
				users.add(tUser);
			}
		});
		res.setMember(users);
		return new OperateResult<Family>(res);
	}

	@Override
	public OperateResult<List<Family>> getMyFamily(String userid) {
		Specification<Family> specification = new Specification<Family>() {
			@Override
			public Predicate toPredicate(Root<Family> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Join<Family, User> join = root.join("member", JoinType.INNER);
				return criteriaBuilder.and(criteriaBuilder.equal(join.get("id"), userid));
			}
		};

		try {
			List<Family> res = new ArrayList<>();
			res = (List<Family>) familyRepository.findAll(specification);

			res.forEach(new Consumer<Family>() {

				@Override
				public void accept(Family tf) {
					List<User> users = new ArrayList<>();
					tf.getMember().forEach(new Consumer<User>() {

						@Override
						public void accept(User user) {
							User tUser = new User();
							tUser.setId(user.getId());
							tUser.setName(user.getName());
							users.add(tUser);
						}
					});
					tf.setMember(users);

					tf.setUpdateTime(null);
					tf.setCreateTime(null);
				}
			});
			return new OperateResult<>(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new OperateResult<>(0, "操作失败");
		}
	}

	@Override
	public OperateResult<String> save(Family family) {
		family.setUpdateTime(new Date());
		Family res = familyRepository.save(family);
		if (res == null)
			return new OperateResult<String>();
		else
			return new OperateResult<String>(res.getId());
	}

	@Override
	public OperateResult<String> join(String fid, User user) {
		if (TextUtils.isEmpty(user.getId())) {
			user = userRepository.save(user);
		}
		Family family = familyRepository.findById(fid).get();
		family.getMember().add(user);
		family = familyRepository.save(family);
		if (family != null)
			return new OperateResult<>();
		else
			return new OperateResult<>(user.getId());
	}

	@Override
	public OperateResult<Object> disJoin(String fid, String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult<Object> del(String fid) {
		// TODO Auto-generated method stub
		return null;
	}
}
