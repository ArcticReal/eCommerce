package com.skytala.eCommerce.domain.product.relations.productCategoryContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryContentType.model.ProductCategoryContentType;
public class ProductCategoryContentTypeFound implements Event{

	private List<ProductCategoryContentType> productCategoryContentTypes;

	public ProductCategoryContentTypeFound(List<ProductCategoryContentType> productCategoryContentTypes) {
		this.productCategoryContentTypes = productCategoryContentTypes;
	}

	public List<ProductCategoryContentType> getProductCategoryContentTypes()	{
		return productCategoryContentTypes;
	}

}
