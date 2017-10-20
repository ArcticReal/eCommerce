package com.skytala.eCommerce.domain.product.relations.product.event.priceRule;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceRule.ProductPriceRule;
public class ProductPriceRuleFound implements Event{

	private List<ProductPriceRule> productPriceRules;

	public ProductPriceRuleFound(List<ProductPriceRule> productPriceRules) {
		this.productPriceRules = productPriceRules;
	}

	public List<ProductPriceRule> getProductPriceRules()	{
		return productPriceRules;
	}

}
