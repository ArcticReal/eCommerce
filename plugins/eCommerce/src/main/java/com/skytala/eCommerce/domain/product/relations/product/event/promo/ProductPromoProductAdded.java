package com.skytala.eCommerce.domain.product.relations.product.event.promo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromoProduct;
public class ProductPromoProductAdded implements Event{

	private ProductPromoProduct addedProductPromoProduct;
	private boolean success;

	public ProductPromoProductAdded(ProductPromoProduct addedProductPromoProduct, boolean success){
		this.addedProductPromoProduct = addedProductPromoProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoProduct getAddedProductPromoProduct() {
		return addedProductPromoProduct;
	}

}
