package com.skytala.eCommerce.domain.product.relations.productPriceRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceRule.model.ProductPriceRule;
public class ProductPriceRuleUpdated implements Event{

	private boolean success;

	public ProductPriceRuleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
