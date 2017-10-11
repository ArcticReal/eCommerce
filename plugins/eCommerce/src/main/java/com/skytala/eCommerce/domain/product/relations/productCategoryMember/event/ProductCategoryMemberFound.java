package com.skytala.eCommerce.domain.product.relations.productCategoryMember.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryMember.model.ProductCategoryMember;
public class ProductCategoryMemberFound implements Event{

	private List<ProductCategoryMember> productCategoryMembers;

	public ProductCategoryMemberFound(List<ProductCategoryMember> productCategoryMembers) {
		this.productCategoryMembers = productCategoryMembers;
	}

	public List<ProductCategoryMember> getProductCategoryMembers()	{
		return productCategoryMembers;
	}

}
