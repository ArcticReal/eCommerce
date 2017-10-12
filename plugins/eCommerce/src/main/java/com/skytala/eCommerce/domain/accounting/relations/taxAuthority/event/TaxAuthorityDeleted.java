package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;
public class TaxAuthorityDeleted implements Event{

	private boolean success;

	public TaxAuthorityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
