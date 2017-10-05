package com.skytala.eCommerce.domain.perfReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.perfReview.model.PerfReview;
public class PerfReviewUpdated implements Event{

	private boolean success;

	public PerfReviewUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
