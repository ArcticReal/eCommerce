package com.skytala.eCommerce.domain.order.relations.returnContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;
public class ReturnContactMechDeleted implements Event{

	private boolean success;

	public ReturnContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
