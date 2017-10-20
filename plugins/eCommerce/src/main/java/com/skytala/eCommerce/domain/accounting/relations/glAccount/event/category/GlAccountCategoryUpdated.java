package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.category.GlAccountCategory;
public class GlAccountCategoryUpdated implements Event{

	private boolean success;

	public GlAccountCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
