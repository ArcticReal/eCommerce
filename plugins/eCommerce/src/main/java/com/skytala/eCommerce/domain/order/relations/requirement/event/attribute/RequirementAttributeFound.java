package com.skytala.eCommerce.domain.order.relations.requirement.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.attribute.RequirementAttribute;
public class RequirementAttributeFound implements Event{

	private List<RequirementAttribute> requirementAttributes;

	public RequirementAttributeFound(List<RequirementAttribute> requirementAttributes) {
		this.requirementAttributes = requirementAttributes;
	}

	public List<RequirementAttribute> getRequirementAttributes()	{
		return requirementAttributes;
	}

}
