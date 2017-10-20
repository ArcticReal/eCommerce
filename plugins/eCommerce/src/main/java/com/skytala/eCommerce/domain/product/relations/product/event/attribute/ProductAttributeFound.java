package com.skytala.eCommerce.domain.product.relations.product.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
public class ProductAttributeFound implements Event{

	private List<ProductAttribute> productAttributes;

	public ProductAttributeFound(List<ProductAttribute> productAttributes) {
		this.productAttributes = productAttributes;
	}

	public List<ProductAttribute> getProductAttributes()	{
		return productAttributes;
	}

}
