package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.taxAuthority;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.taxAuthority.TaxAuthorityGlAccount;
public class TaxAuthorityGlAccountUpdated implements Event{

	private boolean success;

	public TaxAuthorityGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
