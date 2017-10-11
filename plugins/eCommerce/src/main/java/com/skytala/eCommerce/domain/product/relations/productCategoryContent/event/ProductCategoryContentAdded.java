package com.skytala.eCommerce.domain.product.relations.productCategoryContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryContent.model.ProductCategoryContent;
public class ProductCategoryContentAdded implements Event{

	private ProductCategoryContent addedProductCategoryContent;
	private boolean success;

	public ProductCategoryContentAdded(ProductCategoryContent addedProductCategoryContent, boolean success){
		this.addedProductCategoryContent = addedProductCategoryContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryContent getAddedProductCategoryContent() {
		return addedProductCategoryContent;
	}

}
