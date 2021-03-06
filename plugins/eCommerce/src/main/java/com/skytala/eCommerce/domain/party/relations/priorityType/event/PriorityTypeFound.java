package com.skytala.eCommerce.domain.party.relations.priorityType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.priorityType.model.PriorityType;
public class PriorityTypeFound implements Event{

	private List<PriorityType> priorityTypes;

	public PriorityTypeFound(List<PriorityType> priorityTypes) {
		this.priorityTypes = priorityTypes;
	}

	public List<PriorityType> getPriorityTypes()	{
		return priorityTypes;
	}

}
