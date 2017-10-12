package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model.ZipSalesTaxLookup;
public class ZipSalesTaxLookupFound implements Event{

	private List<ZipSalesTaxLookup> zipSalesTaxLookups;

	public ZipSalesTaxLookupFound(List<ZipSalesTaxLookup> zipSalesTaxLookups) {
		this.zipSalesTaxLookups = zipSalesTaxLookups;
	}

	public List<ZipSalesTaxLookup> getZipSalesTaxLookups()	{
		return zipSalesTaxLookups;
	}

}
