package com.skytala.eCommerce.domain.product.relations.productTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productTypeAttr.model.ProductTypeAttr;
public class ProductTypeAttrAdded implements Event{

	private ProductTypeAttr addedProductTypeAttr;
	private boolean success;

	public ProductTypeAttrAdded(ProductTypeAttr addedProductTypeAttr, boolean success){
		this.addedProductTypeAttr = addedProductTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductTypeAttr getAddedProductTypeAttr() {
		return addedProductTypeAttr;
	}

}
