package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;
public class ZipSalesRuleLookupAdded implements Event{

	private ZipSalesRuleLookup addedZipSalesRuleLookup;
	private boolean success;

	public ZipSalesRuleLookupAdded(ZipSalesRuleLookup addedZipSalesRuleLookup, boolean success){
		this.addedZipSalesRuleLookup = addedZipSalesRuleLookup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ZipSalesRuleLookup getAddedZipSalesRuleLookup() {
		return addedZipSalesRuleLookup;
	}

}
