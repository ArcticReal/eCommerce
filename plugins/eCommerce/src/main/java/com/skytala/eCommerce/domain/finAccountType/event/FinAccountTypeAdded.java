package com.skytala.eCommerce.domain.finAccountType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccountType.model.FinAccountType;
public class FinAccountTypeAdded implements Event{

	private FinAccountType addedFinAccountType;
	private boolean success;

	public FinAccountTypeAdded(FinAccountType addedFinAccountType, boolean success){
		this.addedFinAccountType = addedFinAccountType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountType getAddedFinAccountType() {
		return addedFinAccountType;
	}

}
