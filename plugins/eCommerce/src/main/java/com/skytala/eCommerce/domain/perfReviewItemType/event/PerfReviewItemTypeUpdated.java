package com.skytala.eCommerce.domain.perfReviewItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.perfReviewItemType.model.PerfReviewItemType;
public class PerfReviewItemTypeUpdated implements Event{

	private boolean success;

	public PerfReviewItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
