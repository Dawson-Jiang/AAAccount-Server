package com.dawson.aaaccount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dawson.aaaccount.entity.Category;
import com.dawson.aaaccount.entity.Daybook;
import com.dawson.aaaccount.entity.Family;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.repository.FamilyRepository;

@Service("familyService")
public class FamilyServiceImpl implements FamilyService {

	@Resource
	private FamilyRepository familyRepository;

	@Override
	public OperateResult<Family> get(String id) {
		return new OperateResult<Family>(familyRepository.findById(id).get());
	}

	@Override
	public OperateResult<List<Family>> getMyFamily(String userid) {
		Specification<Family> specification = new Specification<Family>() {
			@Override
			public Predicate toPredicate(Root<Family> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				User user = new User();
				user.setId(userid);
				Join<Daybook, User> join = root.join("member", JoinType.LEFT);
				return criteriaBuilder.or(criteriaBuilder.equal(join.get("user_id"), userid));
			}
		};

		try {
			List<Family> res = (List<Family>) familyRepository.findAll(specification);
			if (res == null)
				res = new ArrayList<>();
			return new OperateResult<>(res);
		} catch (Exception e) {
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
	public OperateResult<Object> join(String fid, String uid) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public OperateResult<User> addMember(Family family) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult<Object> delMemeber(String fid, String uid) {
		// TODO Auto-generated method stub
		return null;
	}
}
