package com.skytala.eCommerce.domain.order.relations.returnContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;
public class ReturnContactMechUpdated implements Event{

	private boolean success;

	public ReturnContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
