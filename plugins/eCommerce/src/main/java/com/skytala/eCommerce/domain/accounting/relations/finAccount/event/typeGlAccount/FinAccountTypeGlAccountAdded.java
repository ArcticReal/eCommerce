package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeGlAccount.FinAccountTypeGlAccount;
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
