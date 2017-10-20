package com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;
public class ProductCategoryAttributeAdded implements Event{

	private ProductCategoryAttribute addedProductCategoryAttribute;
	private boolean success;

	public ProductCategoryAttributeAdded(ProductCategoryAttribute addedProductCategoryAttribute, boolean success){
		this.addedProductCategoryAttribute = addedProductCategoryAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryAttribute getAddedProductCategoryAttribute() {
		return addedProductCategoryAttribute;
	}

}
