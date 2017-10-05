package com.skytala.eCommerce.domain.reorderGuideline.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.reorderGuideline.model.ReorderGuideline;
public class ReorderGuidelineDeleted implements Event{

	private boolean success;

	public ReorderGuidelineDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
