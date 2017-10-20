package com.skytala.eCommerce.domain.product.relations.facility.event.productStore;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.productStore.ProductStoreFacility;
public class ProductStoreFacilityFound implements Event{

	private List<ProductStoreFacility> productStoreFacilitys;

	public ProductStoreFacilityFound(List<ProductStoreFacility> productStoreFacilitys) {
		this.productStoreFacilitys = productStoreFacilitys;
	}

	public List<ProductStoreFacility> getProductStoreFacilitys()	{
		return productStoreFacilitys;
	}

}
