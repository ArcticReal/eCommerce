package com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.model.ProductPromoCodeParty;
public class ProductPromoCodePartyDeleted implements Event{

	private boolean success;

	public ProductPromoCodePartyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
