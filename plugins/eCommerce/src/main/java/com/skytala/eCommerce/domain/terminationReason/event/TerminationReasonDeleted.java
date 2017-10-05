package com.skytala.eCommerce.domain.terminationReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.terminationReason.model.TerminationReason;
public class TerminationReasonDeleted implements Event{

	private boolean success;

	public TerminationReasonDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
