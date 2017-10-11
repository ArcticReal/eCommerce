package com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.model.ProductPromoCodeParty;
public class ProductPromoCodePartyAdded implements Event{

	private ProductPromoCodeParty addedProductPromoCodeParty;
	private boolean success;

	public ProductPromoCodePartyAdded(ProductPromoCodeParty addedProductPromoCodeParty, boolean success){
		this.addedProductPromoCodeParty = addedProductPromoCodeParty;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoCodeParty getAddedProductPromoCodeParty() {
		return addedProductPromoCodeParty;
	}

}
