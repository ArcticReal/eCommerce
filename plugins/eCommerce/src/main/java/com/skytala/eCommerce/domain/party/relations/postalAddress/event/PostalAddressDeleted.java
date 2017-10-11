package com.skytala.eCommerce.domain.party.relations.postalAddress.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
public class PostalAddressDeleted implements Event{

	private boolean success;

	public PostalAddressDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
