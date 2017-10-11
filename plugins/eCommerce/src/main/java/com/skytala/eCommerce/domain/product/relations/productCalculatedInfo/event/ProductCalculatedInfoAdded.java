package com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.model.ProductCalculatedInfo;
public class ProductCalculatedInfoAdded implements Event{

	private ProductCalculatedInfo addedProductCalculatedInfo;
	private boolean success;

	public ProductCalculatedInfoAdded(ProductCalculatedInfo addedProductCalculatedInfo, boolean success){
		this.addedProductCalculatedInfo = addedProductCalculatedInfo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCalculatedInfo getAddedProductCalculatedInfo() {
		return addedProductCalculatedInfo;
	}

}
