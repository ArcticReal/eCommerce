package com.skytala.eCommerce.domain.returnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnType.model.ReturnType;
public class ReturnTypeAdded implements Event{

	private ReturnType addedReturnType;
	private boolean success;

	public ReturnTypeAdded(ReturnType addedReturnType, boolean success){
		this.addedReturnType = addedReturnType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnType getAddedReturnType() {
		return addedReturnType;
	}

}
