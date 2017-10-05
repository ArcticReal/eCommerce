package com.skytala.eCommerce.domain.reorderGuideline.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.reorderGuideline.model.ReorderGuideline;
public class ReorderGuidelineAdded implements Event{

	private ReorderGuideline addedReorderGuideline;
	private boolean success;

	public ReorderGuidelineAdded(ReorderGuideline addedReorderGuideline, boolean success){
		this.addedReorderGuideline = addedReorderGuideline;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReorderGuideline getAddedReorderGuideline() {
		return addedReorderGuideline;
	}

}
