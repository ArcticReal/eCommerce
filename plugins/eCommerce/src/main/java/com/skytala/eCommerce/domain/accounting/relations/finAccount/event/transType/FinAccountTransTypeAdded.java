package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transType.FinAccountTransType;
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
