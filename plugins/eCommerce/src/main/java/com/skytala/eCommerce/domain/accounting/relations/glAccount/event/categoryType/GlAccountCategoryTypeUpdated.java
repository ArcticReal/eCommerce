package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryType.GlAccountCategoryType;
public class GlAccountCategoryTypeUpdated implements Event{

	private boolean success;

	public GlAccountCategoryTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
