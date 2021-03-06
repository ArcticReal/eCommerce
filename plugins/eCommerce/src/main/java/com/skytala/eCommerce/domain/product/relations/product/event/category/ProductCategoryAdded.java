package com.skytala.eCommerce.domain.product.relations.product.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
public class ProductCategoryAdded implements Event{

	private ProductCategory addedProductCategory;
	private boolean success;

	public ProductCategoryAdded(ProductCategory addedProductCategory, boolean success){
		this.addedProductCategory = addedProductCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategory getAddedProductCategory() {
		return addedProductCategory;
	}

}
