package com.skytala.eCommerce.domain.product.relations.product.event.promoUse;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoUse.ProductPromoUse;
public class ProductPromoUseAdded implements Event{

	private ProductPromoUse addedProductPromoUse;
	private boolean success;

	public ProductPromoUseAdded(ProductPromoUse addedProductPromoUse, boolean success){
		this.addedProductPromoUse = addedProductPromoUse;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoUse getAddedProductPromoUse() {
		return addedProductPromoUse;
	}

}
