package com.skytala.eCommerce.domain.product.relations.productCategoryLink.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryLink.model.ProductCategoryLink;
public class ProductCategoryLinkUpdated implements Event{

	private boolean success;

	public ProductCategoryLinkUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
