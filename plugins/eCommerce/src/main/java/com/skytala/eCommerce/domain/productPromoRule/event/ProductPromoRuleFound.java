package com.skytala.eCommerce.domain.productPromoRule.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPromoRule.model.ProductPromoRule;
public class ProductPromoRuleFound implements Event{

	private List<ProductPromoRule> productPromoRules;

	public ProductPromoRuleFound(List<ProductPromoRule> productPromoRules) {
		this.productPromoRules = productPromoRules;
	}

	public List<ProductPromoRule> getProductPromoRules()	{
		return productPromoRules;
	}

}
