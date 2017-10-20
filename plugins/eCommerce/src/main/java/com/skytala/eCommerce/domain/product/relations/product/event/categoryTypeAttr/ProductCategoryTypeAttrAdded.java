package com.skytala.eCommerce.domain.product.relations.product.event.categoryTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryTypeAttr.ProductCategoryTypeAttr;
public class ProductCategoryTypeAttrAdded implements Event{

	private ProductCategoryTypeAttr addedProductCategoryTypeAttr;
	private boolean success;

	public ProductCategoryTypeAttrAdded(ProductCategoryTypeAttr addedProductCategoryTypeAttr, boolean success){
		this.addedProductCategoryTypeAttr = addedProductCategoryTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryTypeAttr getAddedProductCategoryTypeAttr() {
		return addedProductCategoryTypeAttr;
	}

}
