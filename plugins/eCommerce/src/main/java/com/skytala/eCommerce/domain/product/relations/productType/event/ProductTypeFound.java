package com.skytala.eCommerce.domain.product.relations.productType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productType.model.ProductType;
public class ProductTypeFound implements Event{

	private List<ProductType> productTypes;

	public ProductTypeFound(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public List<ProductType> getProductTypes()	{
		return productTypes;
	}

}
