package com.skytala.eCommerce.domain.terminationReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.terminationReason.model.TerminationReason;
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
