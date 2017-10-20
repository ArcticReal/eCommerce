package com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.itemType.PerfReviewItemType;
public class PerfReviewItemTypeAdded implements Event{

	private PerfReviewItemType addedPerfReviewItemType;
	private boolean success;

	public PerfReviewItemTypeAdded(PerfReviewItemType addedPerfReviewItemType, boolean success){
		this.addedPerfReviewItemType = addedPerfReviewItemType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PerfReviewItemType getAddedPerfReviewItemType() {
		return addedPerfReviewItemType;
	}

}
