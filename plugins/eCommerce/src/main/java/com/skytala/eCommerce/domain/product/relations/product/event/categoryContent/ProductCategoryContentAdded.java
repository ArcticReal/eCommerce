package com.skytala.eCommerce.domain.product.relations.product.event.categoryContent;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryContent.ProductCategoryContent;
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
