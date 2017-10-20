package com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.fulfillment.GiftCardFulfillment;
public class GiftCardFulfillmentFound implements Event{

	private List<GiftCardFulfillment> giftCardFulfillments;

	public GiftCardFulfillmentFound(List<GiftCardFulfillment> giftCardFulfillments) {
		this.giftCardFulfillments = giftCardFulfillments;
	}

	public List<GiftCardFulfillment> getGiftCardFulfillments()	{
		return giftCardFulfillments;
	}

}
