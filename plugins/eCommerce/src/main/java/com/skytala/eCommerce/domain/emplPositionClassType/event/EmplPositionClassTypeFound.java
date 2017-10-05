package com.skytala.eCommerce.domain.emplPositionClassType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.emplPositionClassType.model.EmplPositionClassType;
public class EmplPositionClassTypeFound implements Event{

	private List<EmplPositionClassType> emplPositionClassTypes;

	public EmplPositionClassTypeFound(List<EmplPositionClassType> emplPositionClassTypes) {
		this.emplPositionClassTypes = emplPositionClassTypes;
	}

	public List<EmplPositionClassType> getEmplPositionClassTypes()	{
		return emplPositionClassTypes;
	}

}
