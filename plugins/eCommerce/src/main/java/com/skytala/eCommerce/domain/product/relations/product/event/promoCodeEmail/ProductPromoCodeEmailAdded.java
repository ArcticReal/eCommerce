package com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeEmail.ProductPromoCodeEmail;
public class ProductPromoCodeEmailAdded implements Event{

	private ProductPromoCodeEmail addedProductPromoCodeEmail;
	private boolean success;

	public ProductPromoCodeEmailAdded(ProductPromoCodeEmail addedProductPromoCodeEmail, boolean success){
		this.addedProductPromoCodeEmail = addedProductPromoCodeEmail;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoCodeEmail getAddedProductPromoCodeEmail() {
		return addedProductPromoCodeEmail;
	}

}
