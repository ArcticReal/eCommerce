package com.skytala.eCommerce.domain.product.relations.productPromoRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoRule.model.ProductPromoRule;
public class ProductPromoRuleUpdated implements Event{

	private boolean success;

	public ProductPromoRuleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
