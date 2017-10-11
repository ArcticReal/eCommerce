package com.skytala.eCommerce.domain.party.relations.priorityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.priorityType.model.PriorityType;
public class PriorityTypeAdded implements Event{

	private PriorityType addedPriorityType;
	private boolean success;

	public PriorityTypeAdded(PriorityType addedPriorityType, boolean success){
		this.addedPriorityType = addedPriorityType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PriorityType getAddedPriorityType() {
		return addedPriorityType;
	}

}
