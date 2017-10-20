package com.skytala.eCommerce.domain.order.relations.returnItem.event.response;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.response.ReturnItemResponse;
public class ReturnItemResponseDeleted implements Event{

	private boolean success;

	public ReturnItemResponseDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
