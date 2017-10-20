package com.skytala.eCommerce.domain.order.relations.returnItem.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.type.ReturnItemType;
public class ReturnItemTypeAdded implements Event{

	private ReturnItemType addedReturnItemType;
	private boolean success;

	public ReturnItemTypeAdded(ReturnItemType addedReturnItemType, boolean success){
		this.addedReturnItemType = addedReturnItemType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnItemType getAddedReturnItemType() {
		return addedReturnItemType;
	}

}
