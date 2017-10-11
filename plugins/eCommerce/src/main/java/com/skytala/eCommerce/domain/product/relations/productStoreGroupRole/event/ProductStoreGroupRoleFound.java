package com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.model.ProductStoreGroupRole;
public class ProductStoreGroupRoleFound implements Event{

	private List<ProductStoreGroupRole> productStoreGroupRoles;

	public ProductStoreGroupRoleFound(List<ProductStoreGroupRole> productStoreGroupRoles) {
		this.productStoreGroupRoles = productStoreGroupRoles;
	}

	public List<ProductStoreGroupRole> getProductStoreGroupRoles()	{
		return productStoreGroupRoles;
	}

}
