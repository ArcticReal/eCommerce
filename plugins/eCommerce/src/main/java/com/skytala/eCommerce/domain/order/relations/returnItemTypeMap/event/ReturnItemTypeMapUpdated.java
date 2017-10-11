package com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.model.ReturnItemTypeMap;
public class ReturnItemTypeMapUpdated implements Event{

	private boolean success;

	public ReturnItemTypeMapUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
