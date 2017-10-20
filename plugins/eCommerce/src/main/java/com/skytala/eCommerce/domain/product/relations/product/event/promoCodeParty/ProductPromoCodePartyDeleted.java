package com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeParty.ProductPromoCodeParty;
public class ProductPromoCodePartyDeleted implements Event{

	private boolean success;

	public ProductPromoCodePartyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
