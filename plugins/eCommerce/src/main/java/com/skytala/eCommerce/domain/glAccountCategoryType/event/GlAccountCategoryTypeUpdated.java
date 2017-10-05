package com.skytala.eCommerce.domain.glAccountCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountCategoryType.model.GlAccountCategoryType;
public class GlAccountCategoryTypeUpdated implements Event{

	private boolean success;

	public GlAccountCategoryTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
