package com.skytala.eCommerce.domain.reorderGuideline.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.reorderGuideline.model.ReorderGuideline;
public class ReorderGuidelineUpdated implements Event{

	private boolean success;

	public ReorderGuidelineUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
