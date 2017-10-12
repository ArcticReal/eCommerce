package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model.ZipSalesTaxLookup;
public class ZipSalesTaxLookupAdded implements Event{

	private ZipSalesTaxLookup addedZipSalesTaxLookup;
	private boolean success;

	public ZipSalesTaxLookupAdded(ZipSalesTaxLookup addedZipSalesTaxLookup, boolean success){
		this.addedZipSalesTaxLookup = addedZipSalesTaxLookup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ZipSalesTaxLookup getAddedZipSalesTaxLookup() {
		return addedZipSalesTaxLookup;
	}

}
