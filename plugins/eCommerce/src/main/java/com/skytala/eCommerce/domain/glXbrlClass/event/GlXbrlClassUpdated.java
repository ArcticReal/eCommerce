package com.skytala.eCommerce.domain.glXbrlClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glXbrlClass.model.GlXbrlClass;
public class GlXbrlClassUpdated implements Event{

	private boolean success;

	public GlXbrlClassUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
