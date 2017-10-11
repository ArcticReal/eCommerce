package com.skytala.eCommerce.domain.product.relations.productContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productContent.model.ProductContent;
public class ProductContentAdded implements Event{

	private ProductContent addedProductContent;
	private boolean success;

	public ProductContentAdded(ProductContent addedProductContent, boolean success){
		this.addedProductContent = addedProductContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductContent getAddedProductContent() {
		return addedProductContent;
	}

}
