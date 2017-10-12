package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model.ProductManufacturingRule;
public class ProductManufacturingRuleFound implements Event{

	private List<ProductManufacturingRule> productManufacturingRules;

	public ProductManufacturingRuleFound(List<ProductManufacturingRule> productManufacturingRules) {
		this.productManufacturingRules = productManufacturingRules;
	}

	public List<ProductManufacturingRule> getProductManufacturingRules()	{
		return productManufacturingRules;
	}

}
