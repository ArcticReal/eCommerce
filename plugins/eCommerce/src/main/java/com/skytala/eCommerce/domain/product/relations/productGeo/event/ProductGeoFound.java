package com.skytala.eCommerce.domain.product.relations.productGeo.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGeo.model.ProductGeo;
public class ProductGeoFound implements Event{

	private List<ProductGeo> productGeos;

	public ProductGeoFound(List<ProductGeo> productGeos) {
		this.productGeos = productGeos;
	}

	public List<ProductGeo> getProductGeos()	{
		return productGeos;
	}

}
