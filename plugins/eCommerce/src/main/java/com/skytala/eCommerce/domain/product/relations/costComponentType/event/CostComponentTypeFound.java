package com.skytala.eCommerce.domain.product.relations.costComponentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponentType.model.CostComponentType;
public class CostComponentTypeFound implements Event{

	private List<CostComponentType> costComponentTypes;

	public CostComponentTypeFound(List<CostComponentType> costComponentTypes) {
		this.costComponentTypes = costComponentTypes;
	}

	public List<CostComponentType> getCostComponentTypes()	{
		return costComponentTypes;
	}

}
