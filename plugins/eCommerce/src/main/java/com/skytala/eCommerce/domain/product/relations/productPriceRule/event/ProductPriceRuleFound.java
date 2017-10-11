package com.skytala.eCommerce.domain.product.relations.productPriceRule.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceRule.model.ProductPriceRule;
public class ProductPriceRuleFound implements Event{

	private List<ProductPriceRule> productPriceRules;

	public ProductPriceRuleFound(List<ProductPriceRule> productPriceRules) {
		this.productPriceRules = productPriceRules;
	}

	public List<ProductPriceRule> getProductPriceRules()	{
		return productPriceRules;
	}

}
