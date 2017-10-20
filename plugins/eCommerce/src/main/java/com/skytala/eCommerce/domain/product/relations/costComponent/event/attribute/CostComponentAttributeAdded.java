package com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute.CostComponentAttribute;
public class CostComponentAttributeAdded implements Event{

	private CostComponentAttribute addedCostComponentAttribute;
	private boolean success;

	public CostComponentAttributeAdded(CostComponentAttribute addedCostComponentAttribute, boolean success){
		this.addedCostComponentAttribute = addedCostComponentAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CostComponentAttribute getAddedCostComponentAttribute() {
		return addedCostComponentAttribute;
	}

}
