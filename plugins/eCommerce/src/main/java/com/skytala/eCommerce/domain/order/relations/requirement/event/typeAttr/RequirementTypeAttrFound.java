package com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.typeAttr.RequirementTypeAttr;
public class RequirementTypeAttrFound implements Event{

	private List<RequirementTypeAttr> requirementTypeAttrs;

	public RequirementTypeAttrFound(List<RequirementTypeAttr> requirementTypeAttrs) {
		this.requirementTypeAttrs = requirementTypeAttrs;
	}

	public List<RequirementTypeAttr> getRequirementTypeAttrs()	{
		return requirementTypeAttrs;
	}

}
