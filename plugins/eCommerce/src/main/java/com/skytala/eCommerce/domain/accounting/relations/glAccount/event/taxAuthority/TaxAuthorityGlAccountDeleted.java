package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.taxAuthority;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.taxAuthority.TaxAuthorityGlAccount;
public class TaxAuthorityGlAccountDeleted implements Event{

	private boolean success;

	public TaxAuthorityGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
