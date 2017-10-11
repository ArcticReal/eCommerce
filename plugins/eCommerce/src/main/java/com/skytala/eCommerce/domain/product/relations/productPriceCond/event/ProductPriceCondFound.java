package com.skytala.eCommerce.domain.product.relations.productPriceCond.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceCond.model.ProductPriceCond;
public class ProductPriceCondFound implements Event{

	private List<ProductPriceCond> productPriceConds;

	public ProductPriceCondFound(List<ProductPriceCond> productPriceConds) {
		this.productPriceConds = productPriceConds;
	}

	public List<ProductPriceCond> getProductPriceConds()	{
		return productPriceConds;
	}

}
