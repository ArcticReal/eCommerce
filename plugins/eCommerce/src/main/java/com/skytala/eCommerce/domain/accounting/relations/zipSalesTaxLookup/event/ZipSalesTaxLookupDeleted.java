package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model.ZipSalesTaxLookup;
public class ZipSalesTaxLookupDeleted implements Event{

	private boolean success;

	public ZipSalesTaxLookupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
