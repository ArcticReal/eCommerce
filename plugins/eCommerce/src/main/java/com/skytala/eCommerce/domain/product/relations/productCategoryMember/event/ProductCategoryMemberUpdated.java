package com.skytala.eCommerce.domain.product.relations.productCategoryMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryMember.model.ProductCategoryMember;
public class ProductCategoryMemberUpdated implements Event{

	private boolean success;

	public ProductCategoryMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
