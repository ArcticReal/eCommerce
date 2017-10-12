package com.skytala.eCommerce.domain.humanres.relations.terminationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.terminationType.model.TerminationType;
public class TerminationTypeDeleted implements Event{

	private boolean success;

	public TerminationTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
