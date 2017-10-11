package com.skytala.eCommerce.domain.product.relations.costComponentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponentAttribute.model.CostComponentAttribute;
public class CostComponentAttributeDeleted implements Event{

	private boolean success;

	public CostComponentAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
