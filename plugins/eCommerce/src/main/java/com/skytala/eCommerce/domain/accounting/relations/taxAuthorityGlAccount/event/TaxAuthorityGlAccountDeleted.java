package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.model.TaxAuthorityGlAccount;
public class TaxAuthorityGlAccountDeleted implements Event{

	private boolean success;

	public TaxAuthorityGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
