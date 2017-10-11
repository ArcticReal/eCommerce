package com.skytala.eCommerce.domain.product.relations.productKeyword.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productKeyword.model.ProductKeyword;
public class ProductKeywordUpdated implements Event{

	private boolean success;

	public ProductKeywordUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
