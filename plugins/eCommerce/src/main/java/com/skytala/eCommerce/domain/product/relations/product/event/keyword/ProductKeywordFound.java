package com.skytala.eCommerce.domain.product.relations.product.event.keyword;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.keyword.ProductKeyword;
public class ProductKeywordFound implements Event{

	private List<ProductKeyword> productKeywords;

	public ProductKeywordFound(List<ProductKeyword> productKeywords) {
		this.productKeywords = productKeywords;
	}

	public List<ProductKeyword> getProductKeywords()	{
		return productKeywords;
	}

}
