package com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.item.PerfReviewItem;
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
