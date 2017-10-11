package com.skytala.eCommerce.domain.product.relations.productPriceType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceType.model.ProductPriceType;
public class ProductPriceTypeFound implements Event{

	private List<ProductPriceType> productPriceTypes;

	public ProductPriceTypeFound(List<ProductPriceType> productPriceTypes) {
		this.productPriceTypes = productPriceTypes;
	}

	public List<ProductPriceType> getProductPriceTypes()	{
		return productPriceTypes;
	}

}
