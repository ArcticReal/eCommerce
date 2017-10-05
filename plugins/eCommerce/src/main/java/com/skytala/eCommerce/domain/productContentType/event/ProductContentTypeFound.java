package com.skytala.eCommerce.domain.productContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productContentType.model.ProductContentType;
public class ProductContentTypeFound implements Event{

	private List<ProductContentType> productContentTypes;

	public ProductContentTypeFound(List<ProductContentType> productContentTypes) {
		this.productContentTypes = productContentTypes;
	}

	public List<ProductContentType> getProductContentTypes()	{
		return productContentTypes;
	}

}
