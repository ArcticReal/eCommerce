package com.skytala.eCommerce.domain.party.relations.addendum.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.addendum.model.Addendum;
public class AddendumAdded implements Event{

	private Addendum addedAddendum;
	private boolean success;

	public AddendumAdded(Addendum addedAddendum, boolean success){
		this.addedAddendum = addedAddendum;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Addendum getAddedAddendum() {
		return addedAddendum;
	}

}
