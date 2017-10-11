package com.skytala.eCommerce.domain.product.relations.productCategoryLink.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryLink.model.ProductCategoryLink;
public class ProductCategoryLinkDeleted implements Event{

	private boolean success;

	public ProductCategoryLinkDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
