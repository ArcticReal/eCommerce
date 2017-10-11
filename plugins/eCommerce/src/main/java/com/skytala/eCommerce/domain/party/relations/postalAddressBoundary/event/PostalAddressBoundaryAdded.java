package com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.model.PostalAddressBoundary;
public class PostalAddressBoundaryAdded implements Event{

	private PostalAddressBoundary addedPostalAddressBoundary;
	private boolean success;

	public PostalAddressBoundaryAdded(PostalAddressBoundary addedPostalAddressBoundary, boolean success){
		this.addedPostalAddressBoundary = addedPostalAddressBoundary;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PostalAddressBoundary getAddedPostalAddressBoundary() {
		return addedPostalAddressBoundary;
	}

}
