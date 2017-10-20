package com.skytala.eCommerce.domain.product.relations.facility.event.product;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.product.ProductFacility;
public class ProductFacilityFound implements Event{

	private List<ProductFacility> productFacilitys;

	public ProductFacilityFound(List<ProductFacility> productFacilitys) {
		this.productFacilitys = productFacilitys;
	}

	public List<ProductFacility> getProductFacilitys()	{
		return productFacilitys;
	}

}
