package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;
public class ZipSalesRuleLookupDeleted implements Event{

	private boolean success;

	public ZipSalesRuleLookupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
