package com.skytala.eCommerce.domain.humanres.relations.perfReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.PerfReview;
public class PerfReviewDeleted implements Event{

	private boolean success;

	public PerfReviewDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
