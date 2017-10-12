package com.skytala.eCommerce.domain.accounting.relations.eftAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;
public class EftAccountAdded implements Event{

	private EftAccount addedEftAccount;
	private boolean success;

	public EftAccountAdded(EftAccount addedEftAccount, boolean success){
		this.addedEftAccount = addedEftAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EftAccount getAddedEftAccount() {
		return addedEftAccount;
	}

}
