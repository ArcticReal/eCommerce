package com.skytala.eCommerce.domain.glAccountCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountCategory.model.GlAccountCategory;
public class GlAccountCategoryUpdated implements Event{

	private boolean success;

	public GlAccountCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
