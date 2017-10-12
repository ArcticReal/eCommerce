package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateType.model.TaxAuthorityRateType;
public class TaxAuthorityRateTypeUpdated implements Event{

	private boolean success;

	public TaxAuthorityRateTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
