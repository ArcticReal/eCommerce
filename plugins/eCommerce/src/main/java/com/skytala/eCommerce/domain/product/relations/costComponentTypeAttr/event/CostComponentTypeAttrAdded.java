package com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.model.CostComponentTypeAttr;
public class CostComponentTypeAttrAdded implements Event{

	private CostComponentTypeAttr addedCostComponentTypeAttr;
	private boolean success;

	public CostComponentTypeAttrAdded(CostComponentTypeAttr addedCostComponentTypeAttr, boolean success){
		this.addedCostComponentTypeAttr = addedCostComponentTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CostComponentTypeAttr getAddedCostComponentTypeAttr() {
		return addedCostComponentTypeAttr;
	}

}
