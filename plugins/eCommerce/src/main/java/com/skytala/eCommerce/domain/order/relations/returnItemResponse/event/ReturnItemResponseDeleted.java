package com.skytala.eCommerce.domain.order.relations.returnItemResponse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemResponse.model.ReturnItemResponse;
public class ReturnItemResponseDeleted implements Event{

	private boolean success;

	public ReturnItemResponseDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
