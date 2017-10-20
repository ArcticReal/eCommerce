package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assocType.TaxAuthorityAssocType;
public class TaxAuthorityAssocTypeUpdated implements Event{

	private boolean success;

	public TaxAuthorityAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
