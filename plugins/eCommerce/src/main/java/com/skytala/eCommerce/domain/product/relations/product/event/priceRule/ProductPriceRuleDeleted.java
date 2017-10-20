package com.skytala.eCommerce.domain.product.relations.product.event.priceRule;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceRule.ProductPriceRule;
public class ProductPriceRuleDeleted implements Event{

	private boolean success;

	public ProductPriceRuleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
