package com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.itemType.PerfReviewItemType;
public class PerfReviewItemTypeDeleted implements Event{

	private boolean success;

	public PerfReviewItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
