package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.auth.FinAccountAuth;
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
