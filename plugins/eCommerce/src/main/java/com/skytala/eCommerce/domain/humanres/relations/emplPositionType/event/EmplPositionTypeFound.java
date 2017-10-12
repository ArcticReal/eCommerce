package com.skytala.eCommerce.domain.humanres.relations.emplPositionType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.model.EmplPositionType;
public class EmplPositionTypeFound implements Event{

	private List<EmplPositionType> emplPositionTypes;

	public EmplPositionTypeFound(List<EmplPositionType> emplPositionTypes) {
		this.emplPositionTypes = emplPositionTypes;
	}

	public List<EmplPositionType> getEmplPositionTypes()	{
		return emplPositionTypes;
	}

}
