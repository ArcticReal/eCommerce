package com.skytala.eCommerce.domain.product.relations.product.event.geo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.geo.ProductGeo;
public class ProductGeoAdded implements Event{

	private ProductGeo addedProductGeo;
	private boolean success;

	public ProductGeoAdded(ProductGeo addedProductGeo, boolean success){
		this.addedProductGeo = addedProductGeo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductGeo getAddedProductGeo() {
		return addedProductGeo;
	}

}
