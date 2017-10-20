package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateType.TaxAuthorityRateType;
public class TaxAuthorityRateTypeDeleted implements Event{

	private boolean success;

	public TaxAuthorityRateTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
