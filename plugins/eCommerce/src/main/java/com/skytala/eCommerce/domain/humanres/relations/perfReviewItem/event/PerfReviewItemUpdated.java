package com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.model.PerfReviewItem;
public class PerfReviewItemUpdated implements Event{

	private boolean success;

	public PerfReviewItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
