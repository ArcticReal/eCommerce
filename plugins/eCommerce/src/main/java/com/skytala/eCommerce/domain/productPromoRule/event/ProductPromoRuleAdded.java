package com.skytala.eCommerce.domain.productPromoRule.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPromoRule.model.ProductPromoRule;
public class ProductPromoRuleAdded implements Event{

	private ProductPromoRule addedProductPromoRule;
	private boolean success;

	public ProductPromoRuleAdded(ProductPromoRule addedProductPromoRule, boolean success){
		this.addedProductPromoRule = addedProductPromoRule;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoRule getAddedProductPromoRule() {
		return addedProductPromoRule;
	}

}
