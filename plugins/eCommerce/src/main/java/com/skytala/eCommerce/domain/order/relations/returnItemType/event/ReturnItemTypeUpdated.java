package com.skytala.eCommerce.domain.order.relations.returnItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemType.model.ReturnItemType;
public class ReturnItemTypeUpdated implements Event{

	private boolean success;

	public ReturnItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
