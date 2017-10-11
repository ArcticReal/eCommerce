package com.skytala.eCommerce.domain.product.relations.productContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productContentType.model.ProductContentType;
public class ProductContentTypeDeleted implements Event{

	private boolean success;

	public ProductContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
