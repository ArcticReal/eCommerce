package com.skytala.eCommerce.domain.emplPositionType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.emplPositionType.model.EmplPositionType;
public class EmplPositionTypeFound implements Event{

	private List<EmplPositionType> emplPositionTypes;

	public EmplPositionTypeFound(List<EmplPositionType> emplPositionTypes) {
		this.emplPositionTypes = emplPositionTypes;
	}

	public List<EmplPositionType> getEmplPositionTypes()	{
		return emplPositionTypes;
	}

}
