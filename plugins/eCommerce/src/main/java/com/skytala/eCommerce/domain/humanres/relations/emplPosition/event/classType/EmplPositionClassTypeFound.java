package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;
public class EmplPositionClassTypeFound implements Event{

	private List<EmplPositionClassType> emplPositionClassTypes;

	public EmplPositionClassTypeFound(List<EmplPositionClassType> emplPositionClassTypes) {
		this.emplPositionClassTypes = emplPositionClassTypes;
	}

	public List<EmplPositionClassType> getEmplPositionClassTypes()	{
		return emplPositionClassTypes;
	}

}
