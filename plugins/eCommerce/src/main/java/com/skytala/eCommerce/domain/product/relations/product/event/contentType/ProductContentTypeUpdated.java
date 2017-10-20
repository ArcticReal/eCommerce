package com.skytala.eCommerce.domain.product.relations.product.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.contentType.ProductContentType;
public class ProductContentTypeUpdated implements Event{

	private boolean success;

	public ProductContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
