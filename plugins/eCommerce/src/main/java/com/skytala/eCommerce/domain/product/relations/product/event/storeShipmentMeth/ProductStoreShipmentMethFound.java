package com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;
public class ProductStoreShipmentMethFound implements Event{

	private List<ProductStoreShipmentMeth> productStoreShipmentMeths;

	public ProductStoreShipmentMethFound(List<ProductStoreShipmentMeth> productStoreShipmentMeths) {
		this.productStoreShipmentMeths = productStoreShipmentMeths;
	}

	public List<ProductStoreShipmentMeth> getProductStoreShipmentMeths()	{
		return productStoreShipmentMeths;
	}

}
