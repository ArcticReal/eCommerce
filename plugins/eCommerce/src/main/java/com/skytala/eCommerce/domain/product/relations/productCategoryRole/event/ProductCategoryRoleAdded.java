package com.skytala.eCommerce.domain.product.relations.productCategoryRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryRole.model.ProductCategoryRole;
public class ProductCategoryRoleAdded implements Event{

	private ProductCategoryRole addedProductCategoryRole;
	private boolean success;

	public ProductCategoryRoleAdded(ProductCategoryRole addedProductCategoryRole, boolean success){
		this.addedProductCategoryRole = addedProductCategoryRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryRole getAddedProductCategoryRole() {
		return addedProductCategoryRole;
	}

}
