package com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.model.ProductCategoryTypeAttr;
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
