package com.skytala.eCommerce.domain.product.relations.product.event.storeRole;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeRole.ProductStoreRole;
public class ProductStoreRoleFound implements Event{

	private List<ProductStoreRole> productStoreRoles;

	public ProductStoreRoleFound(List<ProductStoreRole> productStoreRoles) {
		this.productStoreRoles = productStoreRoles;
	}

	public List<ProductStoreRole> getProductStoreRoles()	{
		return productStoreRoles;
	}

}
