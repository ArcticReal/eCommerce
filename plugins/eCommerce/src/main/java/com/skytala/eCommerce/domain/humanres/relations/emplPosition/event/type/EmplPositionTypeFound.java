package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.type.EmplPositionType;
public class EmplPositionTypeFound implements Event{

	private List<EmplPositionType> emplPositionTypes;

	public EmplPositionTypeFound(List<EmplPositionType> emplPositionTypes) {
		this.emplPositionTypes = emplPositionTypes;
	}

	public List<EmplPositionType> getEmplPositionTypes()	{
		return emplPositionTypes;
	}

}
