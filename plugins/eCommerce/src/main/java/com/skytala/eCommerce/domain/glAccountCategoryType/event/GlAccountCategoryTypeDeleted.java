package com.skytala.eCommerce.domain.glAccountCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountCategoryType.model.GlAccountCategoryType;
public class GlAccountCategoryTypeDeleted implements Event{

	private boolean success;

	public GlAccountCategoryTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
