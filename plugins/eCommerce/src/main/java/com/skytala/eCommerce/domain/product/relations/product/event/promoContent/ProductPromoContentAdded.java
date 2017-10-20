package com.skytala.eCommerce.domain.product.relations.product.event.promoContent;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoContent.ProductPromoContent;
public class ProductPromoContentAdded implements Event{

	private ProductPromoContent addedProductPromoContent;
	private boolean success;

	public ProductPromoContentAdded(ProductPromoContent addedProductPromoContent, boolean success){
		this.addedProductPromoContent = addedProductPromoContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoContent getAddedProductPromoContent() {
		return addedProductPromoContent;
	}

}
