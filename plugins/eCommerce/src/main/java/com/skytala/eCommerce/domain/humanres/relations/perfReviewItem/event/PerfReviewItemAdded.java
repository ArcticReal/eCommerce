package com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.model.PerfReviewItem;
public class PerfReviewItemAdded implements Event{

	private PerfReviewItem addedPerfReviewItem;
	private boolean success;

	public PerfReviewItemAdded(PerfReviewItem addedPerfReviewItem, boolean success){
		this.addedPerfReviewItem = addedPerfReviewItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PerfReviewItem getAddedPerfReviewItem() {
		return addedPerfReviewItem;
	}

}
