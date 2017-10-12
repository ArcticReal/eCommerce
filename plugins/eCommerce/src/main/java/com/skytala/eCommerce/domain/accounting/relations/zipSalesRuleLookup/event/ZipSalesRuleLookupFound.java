package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;
public class ZipSalesRuleLookupFound implements Event{

	private List<ZipSalesRuleLookup> zipSalesRuleLookups;

	public ZipSalesRuleLookupFound(List<ZipSalesRuleLookup> zipSalesRuleLookups) {
		this.zipSalesRuleLookups = zipSalesRuleLookups;
	}

	public List<ZipSalesRuleLookup> getZipSalesRuleLookups()	{
		return zipSalesRuleLookups;
	}

}
