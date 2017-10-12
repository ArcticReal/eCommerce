package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.model.TaxAuthorityAssoc;
public class TaxAuthorityAssocDeleted implements Event{

	private boolean success;

	public TaxAuthorityAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
