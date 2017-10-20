package com.skytala.eCommerce.domain.product.relations.product.event.priceRule;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceRule.ProductPriceRule;
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
