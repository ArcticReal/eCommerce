package com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.model.WorkEffortReview;
public class WorkEffortReviewFound implements Event{

	private List<WorkEffortReview> workEffortReviews;

	public WorkEffortReviewFound(List<WorkEffortReview> workEffortReviews) {
		this.workEffortReviews = workEffortReviews;
	}

	public List<WorkEffortReview> getWorkEffortReviews()	{
		return workEffortReviews;
	}

}
