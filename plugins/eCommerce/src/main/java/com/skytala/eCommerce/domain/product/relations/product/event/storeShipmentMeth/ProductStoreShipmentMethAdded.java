package com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;
public class ProductStoreShipmentMethAdded implements Event{

	private ProductStoreShipmentMeth addedProductStoreShipmentMeth;
	private boolean success;

	public ProductStoreShipmentMethAdded(ProductStoreShipmentMeth addedProductStoreShipmentMeth, boolean success){
		this.addedProductStoreShipmentMeth = addedProductStoreShipmentMeth;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreShipmentMeth getAddedProductStoreShipmentMeth() {
		return addedProductStoreShipmentMeth;
	}

}
