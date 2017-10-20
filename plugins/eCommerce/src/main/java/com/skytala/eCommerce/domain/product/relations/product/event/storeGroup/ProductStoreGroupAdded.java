package com.skytala.eCommerce.domain.product.relations.product.event.storeGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroup.ProductStoreGroup;
public class ProductStoreGroupAdded implements Event{

	private ProductStoreGroup addedProductStoreGroup;
	private boolean success;

	public ProductStoreGroupAdded(ProductStoreGroup addedProductStoreGroup, boolean success){
		this.addedProductStoreGroup = addedProductStoreGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreGroup getAddedProductStoreGroup() {
		return addedProductStoreGroup;
	}

}
