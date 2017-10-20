package com.skytala.eCommerce.domain.product.relations.costComponent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.type.CostComponentType;
public class CostComponentTypeAdded implements Event{

	private CostComponentType addedCostComponentType;
	private boolean success;

	public CostComponentTypeAdded(CostComponentType addedCostComponentType, boolean success){
		this.addedCostComponentType = addedCostComponentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CostComponentType getAddedCostComponentType() {
		return addedCostComponentType;
	}

}
