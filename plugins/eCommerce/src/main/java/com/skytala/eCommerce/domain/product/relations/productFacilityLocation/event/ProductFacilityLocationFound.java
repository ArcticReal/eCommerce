package com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model.ProductFacilityLocation;
public class ProductFacilityLocationFound implements Event{

	private List<ProductFacilityLocation> productFacilityLocations;

	public ProductFacilityLocationFound(List<ProductFacilityLocation> productFacilityLocations) {
		this.productFacilityLocations = productFacilityLocations;
	}

	public List<ProductFacilityLocation> getProductFacilityLocations()	{
		return productFacilityLocations;
	}

}
