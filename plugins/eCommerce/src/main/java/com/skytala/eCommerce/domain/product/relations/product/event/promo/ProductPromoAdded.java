package com.skytala.eCommerce.domain.product.relations.product.event.promo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;
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
