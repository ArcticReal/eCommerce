package com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.typeAttr.CostComponentTypeAttr;
public class CostComponentTypeAttrUpdated implements Event{

	private boolean success;

	public CostComponentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
