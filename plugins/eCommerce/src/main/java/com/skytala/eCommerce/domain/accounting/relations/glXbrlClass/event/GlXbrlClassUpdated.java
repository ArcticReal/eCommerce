package com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.model.GlXbrlClass;
public class GlXbrlClassUpdated implements Event{

	private boolean success;

	public GlXbrlClassUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
