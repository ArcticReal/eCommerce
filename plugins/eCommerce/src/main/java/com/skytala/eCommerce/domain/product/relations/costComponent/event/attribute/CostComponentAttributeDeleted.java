package com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute.CostComponentAttribute;
public class CostComponentAttributeDeleted implements Event{

	private boolean success;

	public CostComponentAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
