package com.skytala.eCommerce.domain.product.relations.product.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.role.ProductRole;
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
