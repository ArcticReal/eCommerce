package com.skytala.eCommerce.domain.productSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productSearchResult.model.ProductSearchResult;
public class ProductSearchResultUpdated implements Event{

	private boolean success;

	public ProductSearchResultUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}