package com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.model.ProductStoreGroupRole;
public class ProductStoreGroupRoleAdded implements Event{

	private ProductStoreGroupRole addedProductStoreGroupRole;
	private boolean success;

	public ProductStoreGroupRoleAdded(ProductStoreGroupRole addedProductStoreGroupRole, boolean success){
		this.addedProductStoreGroupRole = addedProductStoreGroupRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreGroupRole getAddedProductStoreGroupRole() {
		return addedProductStoreGroupRole;
	}

}
