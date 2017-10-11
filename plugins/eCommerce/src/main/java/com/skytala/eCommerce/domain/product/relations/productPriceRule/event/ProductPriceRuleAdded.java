package com.skytala.eCommerce.domain.product.relations.productPriceRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceRule.model.ProductPriceRule;
public class ProductPriceRuleAdded implements Event{

	private ProductPriceRule addedProductPriceRule;
	private boolean success;

	public ProductPriceRuleAdded(ProductPriceRule addedProductPriceRule, boolean success){
		this.addedProductPriceRule = addedProductPriceRule;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPriceRule getAddedProductPriceRule() {
		return addedProductPriceRule;
	}

}
