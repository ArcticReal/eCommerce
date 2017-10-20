package com.skytala.eCommerce.domain.product.relations.product.event.categoryMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryMember.ProductCategoryMember;
public class ProductCategoryMemberAdded implements Event{

	private ProductCategoryMember addedProductCategoryMember;
	private boolean success;

	public ProductCategoryMemberAdded(ProductCategoryMember addedProductCategoryMember, boolean success){
		this.addedProductCategoryMember = addedProductCategoryMember;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryMember getAddedProductCategoryMember() {
		return addedProductCategoryMember;
	}

}
