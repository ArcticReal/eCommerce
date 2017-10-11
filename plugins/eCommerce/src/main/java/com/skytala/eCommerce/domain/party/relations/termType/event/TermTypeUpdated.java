package com.skytala.eCommerce.domain.party.relations.termType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.termType.model.TermType;
public class TermTypeUpdated implements Event{

	private boolean success;

	public TermTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
