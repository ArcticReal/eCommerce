package com.skytala.eCommerce.domain.product.relations.costComponentAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponentAttribute.model.CostComponentAttribute;
public class CostComponentAttributeFound implements Event{

	private List<CostComponentAttribute> costComponentAttributes;

	public CostComponentAttributeFound(List<CostComponentAttribute> costComponentAttributes) {
		this.costComponentAttributes = costComponentAttributes;
	}

	public List<CostComponentAttribute> getCostComponentAttributes()	{
		return costComponentAttributes;
	}

}