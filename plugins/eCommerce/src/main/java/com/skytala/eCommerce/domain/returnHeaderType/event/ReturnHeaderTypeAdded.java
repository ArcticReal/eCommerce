package com.skytala.eCommerce.domain.returnHeaderType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnHeaderType.model.ReturnHeaderType;
public class ReturnHeaderTypeAdded implements Event{

	private ReturnHeaderType addedReturnHeaderType;
	private boolean success;

	public ReturnHeaderTypeAdded(ReturnHeaderType addedReturnHeaderType, boolean success){
		this.addedReturnHeaderType = addedReturnHeaderType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnHeaderType getAddedReturnHeaderType() {
		return addedReturnHeaderType;
	}

}
