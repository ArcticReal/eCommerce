package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.review.WorkEffortReview;
public class WorkEffortReviewDeleted implements Event{

	private boolean success;

	public WorkEffortReviewDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
