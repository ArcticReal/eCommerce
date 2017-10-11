package com.skytala.eCommerce.domain.product.relations.productPromo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromo.model.ProductPromo;
public class ProductPromoAdded implements Event{

	private ProductPromo addedProductPromo;
	private boolean success;

	public ProductPromoAdded(ProductPromo addedProductPromo, boolean success){
		this.addedProductPromo = addedProductPromo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromo getAddedProductPromo() {
		return addedProductPromo;
	}

}
