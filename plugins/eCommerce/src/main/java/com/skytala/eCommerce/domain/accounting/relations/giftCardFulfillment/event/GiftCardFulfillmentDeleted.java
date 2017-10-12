package com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.model.GiftCardFulfillment;
public class GiftCardFulfillmentDeleted implements Event{

	private boolean success;

	public GiftCardFulfillmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
