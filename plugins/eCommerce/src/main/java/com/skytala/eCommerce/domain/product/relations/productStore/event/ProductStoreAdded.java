package com.skytala.eCommerce.domain.product.relations.productStore.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStore.model.ProductStore;
public class ProductStoreAdded implements Event{

	private ProductStore addedProductStore;
	private boolean success;

	public ProductStoreAdded(ProductStore addedProductStore, boolean success){
		this.addedProductStore = addedProductStore;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStore getAddedProductStore() {
		return addedProductStore;
	}

}
