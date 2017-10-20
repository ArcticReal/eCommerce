package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryType.GlAccountCategoryType;
public class GlAccountCategoryTypeDeleted implements Event{

	private boolean success;

	public GlAccountCategoryTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
