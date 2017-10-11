package com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.model.RequirementTypeAttr;
public class RequirementTypeAttrFound implements Event{

	private List<RequirementTypeAttr> requirementTypeAttrs;

	public RequirementTypeAttrFound(List<RequirementTypeAttr> requirementTypeAttrs) {
		this.requirementTypeAttrs = requirementTypeAttrs;
	}

	public List<RequirementTypeAttr> getRequirementTypeAttrs()	{
		return requirementTypeAttrs;
	}

}
