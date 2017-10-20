package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assoc.TaxAuthorityAssoc;
public class TaxAuthorityAssocDeleted implements Event{

	private boolean success;

	public TaxAuthorityAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
