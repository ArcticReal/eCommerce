package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;
public class TaxAuthorityUpdated implements Event{

	private boolean success;

	public TaxAuthorityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
