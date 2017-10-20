package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assoc.TaxAuthorityAssoc;
public class TaxAuthorityAssocUpdated implements Event{

	private boolean success;

	public TaxAuthorityAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
