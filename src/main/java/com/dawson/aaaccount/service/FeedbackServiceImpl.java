package com.dawson.aaaccount.service;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
 
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dawson.aaaccount.entity.Feedback;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User; 
import com.dawson.aaaccount.repository.FeedbackRepository;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

	@Resource
	private FeedbackRepository fRepository;

	
	@Override
	public OperateResult<String> add(Feedback feedback) {
	feedback=	fRepository.save(feedback);
	if(feedback==null)
		return new OperateResult<>();
	else return new OperateResult<>(feedback.getId());
	}

	@Override
	public OperateResult<List<Feedback>> getByUser(User user) {
	 
		Specification<Feedback> specification = new Specification<Feedback>() {
			@Override
			public Predicate toPredicate(Root<Feedback> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
			   query.where(cb.equal(root.get("user").as(User.class), user) )
//					.groupBy(root.get("session_id"))
					.orderBy(cb.desc(root.get("createTime").as(Date.class)));			 
			  	return null;
		  }
		};
	List<Feedback> feedbacks=	fRepository.findAll(specification);
	
	feedbacks.forEach(new Consumer<Feedback>() {

		@Override
		public void accept(Feedback t) {
		  t.setUser(null);			
		}
	});
		return new OperateResult<List<Feedback>>(feedbacks);
	}

}
