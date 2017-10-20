package com.skytala.eCommerce.domain.product.relations.product.event.searchResult;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.searchResult.ProductSearchResult;
public class ProductSearchResultDeleted implements Event{

	private boolean success;

	public ProductSearchResultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
