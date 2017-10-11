package com.skytala.eCommerce.domain.product.relations.productCategoryMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryMember.model.ProductCategoryMember;
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
