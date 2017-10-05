package com.skytala.eCommerce.domain.productPriceRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceRule.model.ProductPriceRule;
public class ProductPriceRuleDeleted implements Event{

	private boolean success;

	public ProductPriceRuleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
