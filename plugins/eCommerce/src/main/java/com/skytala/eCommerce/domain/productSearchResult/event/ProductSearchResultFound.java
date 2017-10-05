package com.skytala.eCommerce.domain.productSearchResult.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productSearchResult.model.ProductSearchResult;
public class ProductSearchResultFound implements Event{

	private List<ProductSearchResult> productSearchResults;

	public ProductSearchResultFound(List<ProductSearchResult> productSearchResults) {
		this.productSearchResults = productSearchResults;
	}

	public List<ProductSearchResult> getProductSearchResults()	{
		return productSearchResults;
	}

}
