package com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.item.PerfReviewItem;
public class PerfReviewItemDeleted implements Event{

	private boolean success;

	public PerfReviewItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
