package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.clazz;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.clazz.GlAccountClass;
public class GlAccountClassDeleted implements Event{

	private boolean success;

	public GlAccountClassDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
