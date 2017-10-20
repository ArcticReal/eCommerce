package com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.typeMap.ReturnItemTypeMap;
public class ReturnItemTypeMapDeleted implements Event{

	private boolean success;

	public ReturnItemTypeMapDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
