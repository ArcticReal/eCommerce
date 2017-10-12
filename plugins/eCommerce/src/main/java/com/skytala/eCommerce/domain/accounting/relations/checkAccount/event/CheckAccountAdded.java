package com.skytala.eCommerce.domain.accounting.relations.checkAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;
public class CheckAccountAdded implements Event{

	private CheckAccount addedCheckAccount;
	private boolean success;

	public CheckAccountAdded(CheckAccount addedCheckAccount, boolean success){
		this.addedCheckAccount = addedCheckAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CheckAccount getAddedCheckAccount() {
		return addedCheckAccount;
	}

}
