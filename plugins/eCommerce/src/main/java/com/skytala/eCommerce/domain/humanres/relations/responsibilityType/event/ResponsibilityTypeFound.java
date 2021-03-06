package com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.model.ResponsibilityType;
public class ResponsibilityTypeFound implements Event{

	private List<ResponsibilityType> responsibilityTypes;

	public ResponsibilityTypeFound(List<ResponsibilityType> responsibilityTypes) {
		this.responsibilityTypes = responsibilityTypes;
	}

	public List<ResponsibilityType> getResponsibilityTypes()	{
		return responsibilityTypes;
	}

}
