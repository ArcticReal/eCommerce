package com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.typeMap.ReturnItemTypeMap;
public class ReturnItemTypeMapUpdated implements Event{

	private boolean success;

	public ReturnItemTypeMapUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
