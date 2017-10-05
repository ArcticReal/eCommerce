package com.skytala.eCommerce.domain.productPriceRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceRule.model.ProductPriceRule;
public class ProductPriceRuleUpdated implements Event{

	private boolean success;

	public ProductPriceRuleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
