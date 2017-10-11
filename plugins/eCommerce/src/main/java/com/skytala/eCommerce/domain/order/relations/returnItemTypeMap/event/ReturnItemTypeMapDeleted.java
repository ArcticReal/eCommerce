package com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.model.ReturnItemTypeMap;
public class ReturnItemTypeMapDeleted implements Event{

	private boolean success;

	public ReturnItemTypeMapDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
