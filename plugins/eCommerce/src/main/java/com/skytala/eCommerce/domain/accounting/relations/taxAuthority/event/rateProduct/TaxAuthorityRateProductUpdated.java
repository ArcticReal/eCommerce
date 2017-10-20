package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct.TaxAuthorityRateProduct;
public class TaxAuthorityRateProductUpdated implements Event{

	private boolean success;

	public TaxAuthorityRateProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
