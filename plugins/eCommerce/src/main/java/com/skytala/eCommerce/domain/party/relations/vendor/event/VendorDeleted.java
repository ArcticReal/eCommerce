package com.skytala.eCommerce.domain.party.relations.vendor.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;
public class VendorDeleted implements Event{

	private boolean success;

	public VendorDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
