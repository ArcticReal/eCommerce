package com.skytala.eCommerce.domain.product.relations.product.event.categoryLink;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryLink.ProductCategoryLink;
public class ProductCategoryLinkDeleted implements Event{

	private boolean success;

	public ProductCategoryLinkDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
