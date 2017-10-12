package com.skytala.eCommerce.domain.accounting.relations.glAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.GlAccount;
public class GlAccountAdded implements Event{

	private GlAccount addedGlAccount;
	private boolean success;

	public GlAccountAdded(GlAccount addedGlAccount, boolean success){
		this.addedGlAccount = addedGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccount getAddedGlAccount() {
		return addedGlAccount;
	}

}
