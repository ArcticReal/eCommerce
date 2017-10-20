package com.skytala.eCommerce.domain.product.relations.product.event.keyword;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.keyword.ProductKeyword;
public class ProductKeywordDeleted implements Event{

	private boolean success;

	public ProductKeywordDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
