package com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.model.PostalAddressBoundary;
public class PostalAddressBoundaryUpdated implements Event{

	private boolean success;

	public PostalAddressBoundaryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
