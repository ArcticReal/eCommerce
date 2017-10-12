package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssocType.model.TaxAuthorityAssocType;
public class TaxAuthorityAssocTypeUpdated implements Event{

	private boolean success;

	public TaxAuthorityAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
