package com.skytala.eCommerce.domain.order.relations.returnItem.event.response;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.response.ReturnItemResponse;
public class ReturnItemResponseUpdated implements Event{

	private boolean success;

	public ReturnItemResponseUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
