package com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.model.CostComponentTypeAttr;
public class CostComponentTypeAttrDeleted implements Event{

	private boolean success;

	public CostComponentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
