package com.skytala.eCommerce.domain.party.relations.addendum.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.addendum.model.Addendum;
public class AddendumFound implements Event{

	private List<Addendum> addendums;

	public AddendumFound(List<Addendum> addendums) {
		this.addendums = addendums;
	}

	public List<Addendum> getAddendums()	{
		return addendums;
	}

}
