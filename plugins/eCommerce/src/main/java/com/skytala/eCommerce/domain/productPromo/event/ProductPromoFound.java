package com.skytala.eCommerce.domain.productPromo.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPromo.model.ProductPromo;
public class ProductPromoFound implements Event{

	private List<ProductPromo> productPromos;

	public ProductPromoFound(List<ProductPromo> productPromos) {
		this.productPromos = productPromos;
	}

	public List<ProductPromo> getProductPromos()	{
		return productPromos;
	}

}
