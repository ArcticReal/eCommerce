package com.skytala.eCommerce.domain.returnItemResponse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnItemResponse.model.ReturnItemResponse;
public class ReturnItemResponseAdded implements Event{

	private ReturnItemResponse addedReturnItemResponse;
	private boolean success;

	public ReturnItemResponseAdded(ReturnItemResponse addedReturnItemResponse, boolean success){
		this.addedReturnItemResponse = addedReturnItemResponse;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnItemResponse getAddedReturnItemResponse() {
		return addedReturnItemResponse;
	}

}
