package com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.itemType.PerfReviewItemType;
public class PerfReviewItemTypeUpdated implements Event{

	private boolean success;

	public PerfReviewItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
