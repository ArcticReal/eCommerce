package com.skytala.eCommerce.domain.glAccountCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountCategory.model.GlAccountCategory;
public class GlAccountCategoryDeleted implements Event{

	private boolean success;

	public GlAccountCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
