package com.skytala.eCommerce.domain.costComponentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.costComponentType.model.CostComponentType;
public class CostComponentTypeFound implements Event{

	private List<CostComponentType> costComponentTypes;

	public CostComponentTypeFound(List<CostComponentType> costComponentTypes) {
		this.costComponentTypes = costComponentTypes;
	}

	public List<CostComponentType> getCostComponentTypes()	{
		return costComponentTypes;
	}

}
