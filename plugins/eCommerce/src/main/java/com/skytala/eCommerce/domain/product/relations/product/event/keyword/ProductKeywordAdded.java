package com.skytala.eCommerce.domain.product.relations.product.event.keyword;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.keyword.ProductKeyword;
public class ProductKeywordAdded implements Event{

	private ProductKeyword addedProductKeyword;
	private boolean success;

	public ProductKeywordAdded(ProductKeyword addedProductKeyword, boolean success){
		this.addedProductKeyword = addedProductKeyword;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductKeyword getAddedProductKeyword() {
		return addedProductKeyword;
	}

}
