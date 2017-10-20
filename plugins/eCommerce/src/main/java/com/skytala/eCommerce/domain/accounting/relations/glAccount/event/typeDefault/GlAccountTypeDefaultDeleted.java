package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault.GlAccountTypeDefault;
public class GlAccountTypeDefaultDeleted implements Event{

	private boolean success;

	public GlAccountTypeDefaultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
