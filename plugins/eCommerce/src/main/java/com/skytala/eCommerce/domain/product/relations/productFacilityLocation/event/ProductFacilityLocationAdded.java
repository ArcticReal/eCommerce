package com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model.ProductFacilityLocation;
public class ProductFacilityLocationAdded implements Event{

	private ProductFacilityLocation addedProductFacilityLocation;
	private boolean success;

	public ProductFacilityLocationAdded(ProductFacilityLocation addedProductFacilityLocation, boolean success){
		this.addedProductFacilityLocation = addedProductFacilityLocation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFacilityLocation getAddedProductFacilityLocation() {
		return addedProductFacilityLocation;
	}

}
