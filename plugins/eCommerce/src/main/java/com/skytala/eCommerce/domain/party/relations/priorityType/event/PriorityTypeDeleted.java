package com.skytala.eCommerce.domain.party.relations.priorityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.priorityType.model.PriorityType;
public class PriorityTypeDeleted implements Event{

	private boolean success;

	public PriorityTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
