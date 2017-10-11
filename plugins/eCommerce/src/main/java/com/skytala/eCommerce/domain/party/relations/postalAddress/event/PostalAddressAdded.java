package com.skytala.eCommerce.domain.party.relations.postalAddress.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
public class PostalAddressAdded implements Event{

	private PostalAddress addedPostalAddress;
	private boolean success;

	public PostalAddressAdded(PostalAddress addedPostalAddress, boolean success){
		this.addedPostalAddress = addedPostalAddress;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PostalAddress getAddedPostalAddress() {
		return addedPostalAddress;
	}

}
