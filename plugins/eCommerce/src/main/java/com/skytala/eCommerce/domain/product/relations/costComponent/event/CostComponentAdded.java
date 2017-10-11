package com.skytala.eCommerce.domain.product.relations.costComponent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.CostComponent;
public class CostComponentAdded implements Event{

	private CostComponent addedCostComponent;
	private boolean success;

	public CostComponentAdded(CostComponent addedCostComponent, boolean success){
		this.addedCostComponent = addedCostComponent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CostComponent getAddedCostComponent() {
		return addedCostComponent;
	}

}
