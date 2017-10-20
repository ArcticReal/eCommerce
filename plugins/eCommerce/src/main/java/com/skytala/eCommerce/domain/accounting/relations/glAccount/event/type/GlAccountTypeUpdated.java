package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;
public class GlAccountTypeUpdated implements Event{

	private boolean success;

	public GlAccountTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
