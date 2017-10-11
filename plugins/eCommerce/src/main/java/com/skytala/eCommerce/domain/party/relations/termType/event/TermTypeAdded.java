package com.skytala.eCommerce.domain.party.relations.termType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.termType.model.TermType;
public class TermTypeAdded implements Event{

	private TermType addedTermType;
	private boolean success;

	public TermTypeAdded(TermType addedTermType, boolean success){
		this.addedTermType = addedTermType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TermType getAddedTermType() {
		return addedTermType;
	}

}
