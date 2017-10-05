package com.skytala.eCommerce.domain.productType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productType.model.ProductType;
public class ProductTypeFound implements Event{

	private List<ProductType> productTypes;

	public ProductTypeFound(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public List<ProductType> getProductTypes()	{
		return productTypes;
	}

}
