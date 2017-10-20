package com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.fulfillment.GiftCardFulfillment;
public class GiftCardFulfillmentAdded implements Event{

	private GiftCardFulfillment addedGiftCardFulfillment;
	private boolean success;

	public GiftCardFulfillmentAdded(GiftCardFulfillment addedGiftCardFulfillment, boolean success){
		this.addedGiftCardFulfillment = addedGiftCardFulfillment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GiftCardFulfillment getAddedGiftCardFulfillment() {
		return addedGiftCardFulfillment;
	}

}
