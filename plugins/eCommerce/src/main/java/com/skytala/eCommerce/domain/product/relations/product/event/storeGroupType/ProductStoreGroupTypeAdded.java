package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupType.ProductStoreGroupType;
public class ProductStoreGroupTypeAdded implements Event{

	private ProductStoreGroupType addedProductStoreGroupType;
	private boolean success;

	public ProductStoreGroupTypeAdded(ProductStoreGroupType addedProductStoreGroupType, boolean success){
		this.addedProductStoreGroupType = addedProductStoreGroupType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreGroupType getAddedProductStoreGroupType() {
		return addedProductStoreGroupType;
	}

}
