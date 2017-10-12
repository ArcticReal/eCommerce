package com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.model.GiftCardFulfillment;
public class GiftCardFulfillmentUpdated implements Event{

	private boolean success;

	public GiftCardFulfillmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
