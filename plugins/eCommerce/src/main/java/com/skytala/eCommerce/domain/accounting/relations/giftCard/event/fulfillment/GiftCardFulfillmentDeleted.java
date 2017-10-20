package com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.fulfillment.GiftCardFulfillment;
public class GiftCardFulfillmentDeleted implements Event{

	private boolean success;

	public GiftCardFulfillmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
