package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeGlAccount.model.FinAccountTypeGlAccount;
public class FinAccountTypeGlAccountAdded implements Event{

	private FinAccountTypeGlAccount addedFinAccountTypeGlAccount;
	private boolean success;

	public FinAccountTypeGlAccountAdded(FinAccountTypeGlAccount addedFinAccountTypeGlAccount, boolean success){
		this.addedFinAccountTypeGlAccount = addedFinAccountTypeGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountTypeGlAccount getAddedFinAccountTypeGlAccount() {
		return addedFinAccountTypeGlAccount;
	}

}
