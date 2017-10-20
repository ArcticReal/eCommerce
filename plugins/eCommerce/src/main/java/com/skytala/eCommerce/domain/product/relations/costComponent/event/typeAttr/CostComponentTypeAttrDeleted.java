package com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.typeAttr.CostComponentTypeAttr;
public class CostComponentTypeAttrDeleted implements Event{

	private boolean success;

	public CostComponentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
