package com.skytala.eCommerce.domain.order.relations.returnItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.ReturnItem;
public class ReturnItemAdded implements Event{

	private ReturnItem addedReturnItem;
	private boolean success;

	public ReturnItemAdded(ReturnItem addedReturnItem, boolean success){
		this.addedReturnItem = addedReturnItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnItem getAddedReturnItem() {
		return addedReturnItem;
	}

}
