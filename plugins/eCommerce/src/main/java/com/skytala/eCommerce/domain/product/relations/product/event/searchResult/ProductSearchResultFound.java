package com.skytala.eCommerce.domain.product.relations.product.event.searchResult;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.searchResult.ProductSearchResult;
public class ProductSearchResultFound implements Event{

	private List<ProductSearchResult> productSearchResults;

	public ProductSearchResultFound(List<ProductSearchResult> productSearchResults) {
		this.productSearchResults = productSearchResults;
	}

	public List<ProductSearchResult> getProductSearchResults()	{
		return productSearchResults;
	}

}
