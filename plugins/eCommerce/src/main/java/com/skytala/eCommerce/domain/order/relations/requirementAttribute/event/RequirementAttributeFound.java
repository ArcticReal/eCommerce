package com.skytala.eCommerce.domain.order.relations.requirementAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementAttribute.model.RequirementAttribute;
public class RequirementAttributeFound implements Event{

	private List<RequirementAttribute> requirementAttributes;

	public RequirementAttributeFound(List<RequirementAttribute> requirementAttributes) {
		this.requirementAttributes = requirementAttributes;
	}

	public List<RequirementAttribute> getRequirementAttributes()	{
		return requirementAttributes;
	}

}
