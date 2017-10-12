package com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.model.WorkEffortReview;
public class WorkEffortReviewUpdated implements Event{

	private boolean success;

	public WorkEffortReviewUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
