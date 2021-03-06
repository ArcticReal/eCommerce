package com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorShipment.ProductStoreVendorShipment;
public class ProductStoreVendorShipmentAdded implements Event{

	private ProductStoreVendorShipment addedProductStoreVendorShipment;
	private boolean success;

	public ProductStoreVendorShipmentAdded(ProductStoreVendorShipment addedProductStoreVendorShipment, boolean success){
		this.addedProductStoreVendorShipment = addedProductStoreVendorShipment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreVendorShipment getAddedProductStoreVendorShipment() {
		return addedProductStoreVendorShipment;
	}

}
