package com.skytala.eCommerce.domain.product.relations.productRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productRole.model.ProductRole;
public class ProductRoleAdded implements Event{

	private ProductRole addedProductRole;
	private boolean success;

	public ProductRoleAdded(ProductRole addedProductRole, boolean success){
		this.addedProductRole = addedProductRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductRole getAddedProductRole() {
		return addedProductRole;
	}

}
