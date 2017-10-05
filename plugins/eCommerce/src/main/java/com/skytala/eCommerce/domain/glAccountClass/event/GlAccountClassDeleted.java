package com.skytala.eCommerce.domain.glAccountClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountClass.model.GlAccountClass;
public class GlAccountClassDeleted implements Event{

	private boolean success;

	public GlAccountClassDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
