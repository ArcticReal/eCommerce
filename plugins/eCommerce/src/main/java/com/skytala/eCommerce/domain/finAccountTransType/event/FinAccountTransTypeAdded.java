package com.skytala.eCommerce.domain.finAccountTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccountTransType.model.FinAccountTransType;
public class FinAccountTransTypeAdded implements Event{

	private FinAccountTransType addedFinAccountTransType;
	private boolean success;

	public FinAccountTransTypeAdded(FinAccountTransType addedFinAccountTransType, boolean success){
		this.addedFinAccountTransType = addedFinAccountTransType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountTransType getAddedFinAccountTransType() {
		return addedFinAccountTransType;
	}

}
