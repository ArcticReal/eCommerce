package com.skytala.eCommerce.domain.product.relations.costComponent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.CostComponent;
public class CostComponentFound implements Event{

	private List<CostComponent> costComponents;

	public CostComponentFound(List<CostComponent> costComponents) {
		this.costComponents = costComponents;
	}

	public List<CostComponent> getCostComponents()	{
		return costComponents;
	}

}
