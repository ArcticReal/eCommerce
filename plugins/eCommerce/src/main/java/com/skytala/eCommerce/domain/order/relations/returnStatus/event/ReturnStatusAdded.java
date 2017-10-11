package com.skytala.eCommerce.domain.order.relations.returnStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnStatus.model.ReturnStatus;
public class ReturnStatusAdded implements Event{

	private ReturnStatus addedReturnStatus;
	private boolean success;

	public ReturnStatusAdded(ReturnStatus addedReturnStatus, boolean success){
		this.addedReturnStatus = addedReturnStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnStatus getAddedReturnStatus() {
		return addedReturnStatus;
	}

}
