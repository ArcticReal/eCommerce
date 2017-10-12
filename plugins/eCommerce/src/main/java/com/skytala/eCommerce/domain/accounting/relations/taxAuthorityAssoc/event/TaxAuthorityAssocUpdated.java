package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.model.TaxAuthorityAssoc;
public class TaxAuthorityAssocUpdated implements Event{

	private boolean success;

	public TaxAuthorityAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
