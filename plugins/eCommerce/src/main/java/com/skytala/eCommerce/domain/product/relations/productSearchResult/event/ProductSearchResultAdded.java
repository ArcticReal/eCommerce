package com.skytala.eCommerce.domain.product.relations.productSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSearchResult.model.ProductSearchResult;
public class ProductSearchResultAdded implements Event{

	private ProductSearchResult addedProductSearchResult;
	private boolean success;

	public ProductSearchResultAdded(ProductSearchResult addedProductSearchResult, boolean success){
		this.addedProductSearchResult = addedProductSearchResult;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductSearchResult getAddedProductSearchResult() {
		return addedProductSearchResult;
	}

}
