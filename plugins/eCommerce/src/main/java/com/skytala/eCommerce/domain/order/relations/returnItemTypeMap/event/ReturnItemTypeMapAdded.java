package com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.model.ReturnItemTypeMap;
public class ReturnItemTypeMapAdded implements Event{

	private ReturnItemTypeMap addedReturnItemTypeMap;
	private boolean success;

	public ReturnItemTypeMapAdded(ReturnItemTypeMap addedReturnItemTypeMap, boolean success){
		this.addedReturnItemTypeMap = addedReturnItemTypeMap;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnItemTypeMap getAddedReturnItemTypeMap() {
		return addedReturnItemTypeMap;
	}

}
