package com.skytala.eCommerce.domain.product.relations.product.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
public class ProductAttributeAdded implements Event{

	private ProductAttribute addedProductAttribute;
	private boolean success;

	public ProductAttributeAdded(ProductAttribute addedProductAttribute, boolean success){
		this.addedProductAttribute = addedProductAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductAttribute getAddedProductAttribute() {
		return addedProductAttribute;
	}

}
