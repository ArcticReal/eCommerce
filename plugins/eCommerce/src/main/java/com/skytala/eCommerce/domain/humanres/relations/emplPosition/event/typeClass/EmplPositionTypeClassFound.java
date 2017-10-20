package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeClass.EmplPositionTypeClass;
public class EmplPositionTypeClassFound implements Event{

	private List<EmplPositionTypeClass> emplPositionTypeClasss;

	public EmplPositionTypeClassFound(List<EmplPositionTypeClass> emplPositionTypeClasss) {
		this.emplPositionTypeClasss = emplPositionTypeClasss;
	}

	public List<EmplPositionTypeClass> getEmplPositionTypeClasss()	{
		return emplPositionTypeClasss;
	}

}
