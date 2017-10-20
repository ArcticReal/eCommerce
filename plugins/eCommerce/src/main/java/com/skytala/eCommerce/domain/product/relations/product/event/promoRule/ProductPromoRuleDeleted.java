package com.skytala.eCommerce.domain.product.relations.product.event.promoRule;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoRule.ProductPromoRule;
public class ProductPromoRuleDeleted implements Event{

	private boolean success;

	public ProductPromoRuleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
