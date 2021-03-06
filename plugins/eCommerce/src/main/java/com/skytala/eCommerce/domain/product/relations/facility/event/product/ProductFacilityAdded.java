package com.skytala.eCommerce.domain.product.relations.facility.event.product;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.product.ProductFacility;
public class ProductFacilityAdded implements Event{

	private ProductFacility addedProductFacility;
	private boolean success;

	public ProductFacilityAdded(ProductFacility addedProductFacility, boolean success){
		this.addedProductFacility = addedProductFacility;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFacility getAddedProductFacility() {
		return addedProductFacility;
	}

}
