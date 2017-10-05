package com.skytala.eCommerce.domain.returnItemResponse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnItemResponse.model.ReturnItemResponse;
public class ReturnItemResponseUpdated implements Event{

	private boolean success;

	public ReturnItemResponseUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
