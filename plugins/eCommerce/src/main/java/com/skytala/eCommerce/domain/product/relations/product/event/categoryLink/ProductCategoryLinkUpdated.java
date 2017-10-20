package com.skytala.eCommerce.domain.product.relations.product.event.categoryLink;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryLink.ProductCategoryLink;
public class ProductCategoryLinkUpdated implements Event{

	private boolean success;

	public ProductCategoryLinkUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
