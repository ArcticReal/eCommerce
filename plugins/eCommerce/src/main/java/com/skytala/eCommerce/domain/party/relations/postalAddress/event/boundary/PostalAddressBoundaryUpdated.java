package com.skytala.eCommerce.domain.party.relations.postalAddress.event.boundary;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.postalAddress.model.boundary.PostalAddressBoundary;
public class PostalAddressBoundaryUpdated implements Event{

	private boolean success;

	public PostalAddressBoundaryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
