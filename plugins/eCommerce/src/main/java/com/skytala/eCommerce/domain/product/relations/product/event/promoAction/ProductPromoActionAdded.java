package com.skytala.eCommerce.domain.product.relations.product.event.promoAction;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoAction.ProductPromoAction;
public class ProductPromoActionAdded implements Event{

	private ProductPromoAction addedProductPromoAction;
	private boolean success;

	public ProductPromoActionAdded(ProductPromoAction addedProductPromoAction, boolean success){
		this.addedProductPromoAction = addedProductPromoAction;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoAction getAddedProductPromoAction() {
		return addedProductPromoAction;
	}

}
