package com.skytala.eCommerce.domain.product.relations.product.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.typeAttr.ProductTypeAttr;
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
