package com.skytala.eCommerce.domain.product.relations.product.event.categoryRole;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryRole.ProductCategoryRole;
public class ProductCategoryRoleFound implements Event{

	private List<ProductCategoryRole> productCategoryRoles;

	public ProductCategoryRoleFound(List<ProductCategoryRole> productCategoryRoles) {
		this.productCategoryRoles = productCategoryRoles;
	}

	public List<ProductCategoryRole> getProductCategoryRoles()	{
		return productCategoryRoles;
	}

}
