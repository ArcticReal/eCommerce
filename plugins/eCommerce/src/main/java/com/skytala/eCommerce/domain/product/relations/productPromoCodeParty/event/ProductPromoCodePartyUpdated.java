package com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.model.ProductPromoCodeParty;
public class ProductPromoCodePartyUpdated implements Event{

	private boolean success;

	public ProductPromoCodePartyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
