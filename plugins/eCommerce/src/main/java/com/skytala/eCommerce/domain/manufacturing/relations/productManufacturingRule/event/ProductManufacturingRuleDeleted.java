package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model.ProductManufacturingRule;
public class ProductManufacturingRuleDeleted implements Event{

	private boolean success;

	public ProductManufacturingRuleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}