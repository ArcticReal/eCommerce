package com.skytala.eCommerce.domain.product.relations.productStoreRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreRole.model.ProductStoreRole;
public class ProductStoreRoleAdded implements Event{

	private ProductStoreRole addedProductStoreRole;
	private boolean success;

	public ProductStoreRoleAdded(ProductStoreRole addedProductStoreRole, boolean success){
		this.addedProductStoreRole = addedProductStoreRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreRole getAddedProductStoreRole() {
		return addedProductStoreRole;
	}

}
