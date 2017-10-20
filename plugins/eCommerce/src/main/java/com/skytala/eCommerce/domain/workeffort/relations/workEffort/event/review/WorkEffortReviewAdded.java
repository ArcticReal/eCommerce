package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.review.WorkEffortReview;
public class WorkEffortReviewAdded implements Event{

	private WorkEffortReview addedWorkEffortReview;
	private boolean success;

	public WorkEffortReviewAdded(WorkEffortReview addedWorkEffortReview, boolean success){
		this.addedWorkEffortReview = addedWorkEffortReview;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortReview getAddedWorkEffortReview() {
		return addedWorkEffortReview;
	}

}
