package com.skytala.eCommerce.domain.accounting.relations.finAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;
public class FinAccountAdded implements Event{

	private FinAccount addedFinAccount;
	private boolean success;

	public FinAccountAdded(FinAccount addedFinAccount, boolean success){
		this.addedFinAccount = addedFinAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccount getAddedFinAccount() {
		return addedFinAccount;
	}

}
