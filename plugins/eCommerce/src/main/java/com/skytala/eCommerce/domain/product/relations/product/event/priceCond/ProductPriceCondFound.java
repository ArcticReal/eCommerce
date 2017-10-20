package com.skytala.eCommerce.domain.product.relations.product.event.priceCond;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceCond.ProductPriceCond;
public class ProductPriceCondFound implements Event{

	private List<ProductPriceCond> productPriceConds;

	public ProductPriceCondFound(List<ProductPriceCond> productPriceConds) {
		this.productPriceConds = productPriceConds;
	}

	public List<ProductPriceCond> getProductPriceConds()	{
		return productPriceConds;
	}

}
