package com.skytala.eCommerce.domain.product.relations.productPromoCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCode.model.ProductPromoCode;
public class ProductPromoCodeAdded implements Event{

	private ProductPromoCode addedProductPromoCode;
	private boolean success;

	public ProductPromoCodeAdded(ProductPromoCode addedProductPromoCode, boolean success){
		this.addedProductPromoCode = addedProductPromoCode;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoCode getAddedProductPromoCode() {
		return addedProductPromoCode;
	}

}
