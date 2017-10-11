package com.skytala.eCommerce.domain.product.relations.productStoreRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreRole.model.ProductStoreRole;
public class ProductStoreRoleFound implements Event{

	private List<ProductStoreRole> productStoreRoles;

	public ProductStoreRoleFound(List<ProductStoreRole> productStoreRoles) {
		this.productStoreRoles = productStoreRoles;
	}

	public List<ProductStoreRole> getProductStoreRoles()	{
		return productStoreRoles;
	}

}
