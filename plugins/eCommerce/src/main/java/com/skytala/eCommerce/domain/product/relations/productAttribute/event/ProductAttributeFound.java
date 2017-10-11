package com.skytala.eCommerce.domain.product.relations.productAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productAttribute.model.ProductAttribute;
public class ProductAttributeFound implements Event{

	private List<ProductAttribute> productAttributes;

	public ProductAttributeFound(List<ProductAttribute> productAttributes) {
		this.productAttributes = productAttributes;
	}

	public List<ProductAttribute> getProductAttributes()	{
		return productAttributes;
	}

}
