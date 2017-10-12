package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model.FinAccountStatus;
public class FinAccountStatusAdded implements Event{

	private FinAccountStatus addedFinAccountStatus;
	private boolean success;

	public FinAccountStatusAdded(FinAccountStatus addedFinAccountStatus, boolean success){
		this.addedFinAccountStatus = addedFinAccountStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountStatus getAddedFinAccountStatus() {
		return addedFinAccountStatus;
	}

}
