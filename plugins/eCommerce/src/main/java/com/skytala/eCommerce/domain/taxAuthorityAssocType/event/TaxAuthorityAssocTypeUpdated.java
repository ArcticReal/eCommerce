package com.skytala.eCommerce.domain.taxAuthorityAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.taxAuthorityAssocType.model.TaxAuthorityAssocType;
public class TaxAuthorityAssocTypeUpdated implements Event{

	private boolean success;

	public TaxAuthorityAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
