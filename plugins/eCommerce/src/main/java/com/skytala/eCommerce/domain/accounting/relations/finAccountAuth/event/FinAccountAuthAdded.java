package com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.model.FinAccountAuth;
public class FinAccountAuthAdded implements Event{

	private FinAccountAuth addedFinAccountAuth;
	private boolean success;

	public FinAccountAuthAdded(FinAccountAuth addedFinAccountAuth, boolean success){
		this.addedFinAccountAuth = addedFinAccountAuth;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountAuth getAddedFinAccountAuth() {
		return addedFinAccountAuth;
	}

}
