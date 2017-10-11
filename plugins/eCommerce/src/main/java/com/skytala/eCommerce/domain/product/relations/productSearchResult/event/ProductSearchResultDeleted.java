package com.skytala.eCommerce.domain.product.relations.productSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSearchResult.model.ProductSearchResult;
public class ProductSearchResultDeleted implements Event{

	private boolean success;

	public ProductSearchResultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
