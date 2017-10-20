package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.review.WorkEffortReview;
public class WorkEffortReviewFound implements Event{

	private List<WorkEffortReview> workEffortReviews;

	public WorkEffortReviewFound(List<WorkEffortReview> workEffortReviews) {
		this.workEffortReviews = workEffortReviews;
	}

	public List<WorkEffortReview> getWorkEffortReviews()	{
		return workEffortReviews;
	}

}
