package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.ProductStore;

public class ProductStoreAdded implements Event {

	private ProductStore addedProductStore;
	private boolean success;

	public ProductStoreAdded(ProductStore addedProductStore, boolean success) {
		this.addedProductStore = addedProductStore;
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ProductStore getAddedProductStore() {
		return addedProductStore;
	}

	public void setAddedProductStore(ProductStore addedProductStore) {
		this.addedProductStore = addedProductStore;
	}
}
