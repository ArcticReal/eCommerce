package com.skytala.eCommerce.domain.productContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productContentType.model.ProductContentType;
public class ProductContentTypeUpdated implements Event{

	private boolean success;

	public ProductContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
