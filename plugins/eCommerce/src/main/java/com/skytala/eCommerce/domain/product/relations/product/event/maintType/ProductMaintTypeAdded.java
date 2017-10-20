package com.skytala.eCommerce.domain.product.relations.product.event.maintType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.maintType.ProductMaintType;
public class ProductMaintTypeAdded implements Event{

	private ProductMaintType addedProductMaintType;
	private boolean success;

	public ProductMaintTypeAdded(ProductMaintType addedProductMaintType, boolean success){
		this.addedProductMaintType = addedProductMaintType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductMaintType getAddedProductMaintType() {
		return addedProductMaintType;
	}

}
