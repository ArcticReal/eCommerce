package com.skytala.eCommerce.domain.party.relations.vendor.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;
public class VendorAdded implements Event{

	private Vendor addedVendor;
	private boolean success;

	public VendorAdded(Vendor addedVendor, boolean success){
		this.addedVendor = addedVendor;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Vendor getAddedVendor() {
		return addedVendor;
	}

}
