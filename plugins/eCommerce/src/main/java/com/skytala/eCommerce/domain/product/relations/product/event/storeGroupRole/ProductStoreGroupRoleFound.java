package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRole.ProductStoreGroupRole;
public class ProductStoreGroupRoleFound implements Event{

	private List<ProductStoreGroupRole> productStoreGroupRoles;

	public ProductStoreGroupRoleFound(List<ProductStoreGroupRole> productStoreGroupRoles) {
		this.productStoreGroupRoles = productStoreGroupRoles;
	}

	public List<ProductStoreGroupRole> getProductStoreGroupRoles()	{
		return productStoreGroupRoles;
	}

}
