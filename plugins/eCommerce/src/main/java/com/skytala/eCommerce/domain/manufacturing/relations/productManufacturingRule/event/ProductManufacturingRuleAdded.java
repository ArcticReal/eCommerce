package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model.ProductManufacturingRule;
public class ProductManufacturingRuleAdded implements Event{

	private ProductManufacturingRule addedProductManufacturingRule;
	private boolean success;

	public ProductManufacturingRuleAdded(ProductManufacturingRule addedProductManufacturingRule, boolean success){
		this.addedProductManufacturingRule = addedProductManufacturingRule;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductManufacturingRule getAddedProductManufacturingRule() {
		return addedProductManufacturingRule;
	}

}
