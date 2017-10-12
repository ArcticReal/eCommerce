package com.skytala.eCommerce.domain.accounting.relations.glAccountClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.model.GlAccountClass;
public class GlAccountClassUpdated implements Event{

	private boolean success;

	public GlAccountClassUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
