package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateProduct.model.TaxAuthorityRateProduct;
public class TaxAuthorityRateProductUpdated implements Event{

	private boolean success;

	public TaxAuthorityRateProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
