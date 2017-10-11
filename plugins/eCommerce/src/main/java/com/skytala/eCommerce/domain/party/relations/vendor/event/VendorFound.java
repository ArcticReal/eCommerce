package com.skytala.eCommerce.domain.party.relations.vendor.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;
public class VendorFound implements Event{

	private List<Vendor> vendors;

	public VendorFound(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public List<Vendor> getVendors()	{
		return vendors;
	}

}
