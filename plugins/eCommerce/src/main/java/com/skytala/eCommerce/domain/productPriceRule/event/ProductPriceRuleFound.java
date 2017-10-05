package com.skytala.eCommerce.domain.productPriceRule.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceRule.model.ProductPriceRule;
public class ProductPriceRuleFound implements Event{

	private List<ProductPriceRule> productPriceRules;

	public ProductPriceRuleFound(List<ProductPriceRule> productPriceRules) {
		this.productPriceRules = productPriceRules;
	}

	public List<ProductPriceRule> getProductPriceRules()	{
		return productPriceRules;
	}

}
