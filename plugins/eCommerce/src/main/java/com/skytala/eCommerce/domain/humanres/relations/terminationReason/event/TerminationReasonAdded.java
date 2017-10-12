package com.skytala.eCommerce.domain.humanres.relations.terminationReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.terminationReason.model.TerminationReason;
public class TerminationReasonAdded implements Event{

	private TerminationReason addedTerminationReason;
	private boolean success;

	public TerminationReasonAdded(TerminationReason addedTerminationReason, boolean success){
		this.addedTerminationReason = addedTerminationReason;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TerminationReason getAddedTerminationReason() {
		return addedTerminationReason;
	}

}
