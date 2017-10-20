package com.skytala.eCommerce.domain.product.relations.product.event.categoryRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryRole.ProductCategoryRole;
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
