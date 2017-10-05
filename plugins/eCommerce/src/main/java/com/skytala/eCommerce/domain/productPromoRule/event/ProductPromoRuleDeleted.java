package com.skytala.eCommerce.domain.productPromoRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPromoRule.model.ProductPromoRule;
public class ProductPromoRuleDeleted implements Event{

	private boolean success;

	public ProductPromoRuleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
