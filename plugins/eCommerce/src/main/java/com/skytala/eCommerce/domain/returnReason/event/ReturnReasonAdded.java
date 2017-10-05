package com.skytala.eCommerce.domain.returnReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnReason.model.ReturnReason;
public class ReturnReasonAdded implements Event{

	private ReturnReason addedReturnReason;
	private boolean success;

	public ReturnReasonAdded(ReturnReason addedReturnReason, boolean success){
		this.addedReturnReason = addedReturnReason;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnReason getAddedReturnReason() {
		return addedReturnReason;
	}

}
