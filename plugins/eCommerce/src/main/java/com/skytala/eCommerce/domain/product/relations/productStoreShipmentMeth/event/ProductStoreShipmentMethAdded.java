package com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.model.ProductStoreShipmentMeth;
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
