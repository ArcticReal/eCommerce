package com.skytala.eCommerce.domain.returnItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnItemType.model.ReturnItemType;
public class ReturnItemTypeDeleted implements Event{

	private boolean success;

	public ReturnItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
