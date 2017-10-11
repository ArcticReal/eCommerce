package com.skytala.eCommerce.domain.product.relations.reorderGuideline.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.reorderGuideline.model.ReorderGuideline;
public class ReorderGuidelineDeleted implements Event{

	private boolean success;

	public ReorderGuidelineDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
