package com.skytala.eCommerce.domain.perfReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.perfReview.model.PerfReview;
public class PerfReviewAdded implements Event{

	private PerfReview addedPerfReview;
	private boolean success;

	public PerfReviewAdded(PerfReview addedPerfReview, boolean success){
		this.addedPerfReview = addedPerfReview;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PerfReview getAddedPerfReview() {
		return addedPerfReview;
	}

}
