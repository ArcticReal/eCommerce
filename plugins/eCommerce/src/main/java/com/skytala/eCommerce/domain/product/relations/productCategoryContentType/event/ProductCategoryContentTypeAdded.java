package com.skytala.eCommerce.domain.product.relations.productCategoryContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryContentType.model.ProductCategoryContentType;
public class ProductCategoryContentTypeAdded implements Event{

	private ProductCategoryContentType addedProductCategoryContentType;
	private boolean success;

	public ProductCategoryContentTypeAdded(ProductCategoryContentType addedProductCategoryContentType, boolean success){
		this.addedProductCategoryContentType = addedProductCategoryContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryContentType getAddedProductCategoryContentType() {
		return addedProductCategoryContentType;
	}

}
