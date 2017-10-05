package com.skytala.eCommerce.domain.terminationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.terminationType.model.TerminationType;
public class TerminationTypeAdded implements Event{

	private TerminationType addedTerminationType;
	private boolean success;

	public TerminationTypeAdded(TerminationType addedTerminationType, boolean success){
		this.addedTerminationType = addedTerminationType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TerminationType getAddedTerminationType() {
		return addedTerminationType;
	}

}
