package com.skytala.eCommerce.domain.order.relations.returnItem.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.type.ReturnItemType;
public class ReturnItemTypeDeleted implements Event{

	private boolean success;

	public ReturnItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
