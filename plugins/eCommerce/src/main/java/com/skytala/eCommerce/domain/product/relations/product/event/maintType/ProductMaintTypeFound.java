package com.skytala.eCommerce.domain.product.relations.product.event.maintType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.maintType.ProductMaintType;
public class ProductMaintTypeFound implements Event{

	private List<ProductMaintType> productMaintTypes;

	public ProductMaintTypeFound(List<ProductMaintType> productMaintTypes) {
		this.productMaintTypes = productMaintTypes;
	}

	public List<ProductMaintType> getProductMaintTypes()	{
		return productMaintTypes;
	}

}
