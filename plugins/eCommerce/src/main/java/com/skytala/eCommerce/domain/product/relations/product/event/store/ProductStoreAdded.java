package com.skytala.eCommerce.domain.product.relations.product.event.store;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.store.ProductStore;
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
