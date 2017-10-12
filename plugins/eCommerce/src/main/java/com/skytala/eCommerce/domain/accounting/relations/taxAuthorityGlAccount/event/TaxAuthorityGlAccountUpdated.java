package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.model.TaxAuthorityGlAccount;
public class TaxAuthorityGlAccountUpdated implements Event{

	private boolean success;

	public TaxAuthorityGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
