package com.skytala.eCommerce.domain.productContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productContentType.model.ProductContentType;
public class ProductContentTypeAdded implements Event{

	private ProductContentType addedProductContentType;
	private boolean success;

	public ProductContentTypeAdded(ProductContentType addedProductContentType, boolean success){
		this.addedProductContentType = addedProductContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductContentType getAddedProductContentType() {
		return addedProductContentType;
	}

}
