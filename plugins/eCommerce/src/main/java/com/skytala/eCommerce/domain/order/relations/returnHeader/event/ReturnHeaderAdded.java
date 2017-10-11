package com.skytala.eCommerce.domain.order.relations.returnHeader.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;
public class ReturnHeaderAdded implements Event{

	private ReturnHeader addedReturnHeader;
	private boolean success;

	public ReturnHeaderAdded(ReturnHeader addedReturnHeader, boolean success){
		this.addedReturnHeader = addedReturnHeader;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnHeader getAddedReturnHeader() {
		return addedReturnHeader;
	}

}
