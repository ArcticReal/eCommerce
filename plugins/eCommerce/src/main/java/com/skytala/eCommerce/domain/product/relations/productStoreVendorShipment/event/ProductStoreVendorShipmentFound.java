package com.skytala.eCommerce.domain.product.relations.productStoreVendorShipment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreVendorShipment.model.ProductStoreVendorShipment;
public class ProductStoreVendorShipmentFound implements Event{

	private List<ProductStoreVendorShipment> productStoreVendorShipments;

	public ProductStoreVendorShipmentFound(List<ProductStoreVendorShipment> productStoreVendorShipments) {
		this.productStoreVendorShipments = productStoreVendorShipments;
	}

	public List<ProductStoreVendorShipment> getProductStoreVendorShipments()	{
		return productStoreVendorShipments;
	}

}
