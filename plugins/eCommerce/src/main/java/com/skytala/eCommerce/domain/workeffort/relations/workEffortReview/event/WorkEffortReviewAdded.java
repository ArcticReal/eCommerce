package com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.model.WorkEffortReview;
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
