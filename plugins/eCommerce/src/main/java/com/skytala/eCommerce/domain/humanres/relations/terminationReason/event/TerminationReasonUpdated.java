package com.skytala.eCommerce.domain.humanres.relations.terminationReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.terminationReason.model.TerminationReason;
public class TerminationReasonUpdated implements Event{

	private boolean success;

	public TerminationReasonUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
