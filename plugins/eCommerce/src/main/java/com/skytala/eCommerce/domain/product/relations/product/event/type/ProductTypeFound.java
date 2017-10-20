package com.skytala.eCommerce.domain.product.relations.product.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.type.ProductType;
public class ProductTypeFound implements Event{

	private List<ProductType> productTypes;

	public ProductTypeFound(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public List<ProductType> getProductTypes()	{
		return productTypes;
	}

}
