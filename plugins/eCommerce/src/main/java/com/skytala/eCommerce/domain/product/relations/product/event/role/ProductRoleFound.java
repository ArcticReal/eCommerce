package com.skytala.eCommerce.domain.product.relations.product.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.role.ProductRole;
public class ProductRoleFound implements Event{

	private List<ProductRole> productRoles;

	public ProductRoleFound(List<ProductRole> productRoles) {
		this.productRoles = productRoles;
	}

	public List<ProductRole> getProductRoles()	{
		return productRoles;
	}

}
