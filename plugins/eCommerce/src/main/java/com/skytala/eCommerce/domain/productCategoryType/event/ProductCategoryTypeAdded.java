package com.skytala.eCommerce.domain.productCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productCategoryType.model.ProductCategoryType;
public class ProductCategoryTypeAdded implements Event{

	private ProductCategoryType addedProductCategoryType;
	private boolean success;

	public ProductCategoryTypeAdded(ProductCategoryType addedProductCategoryType, boolean success){
		this.addedProductCategoryType = addedProductCategoryType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryType getAddedProductCategoryType() {
		return addedProductCategoryType;
	}

}
