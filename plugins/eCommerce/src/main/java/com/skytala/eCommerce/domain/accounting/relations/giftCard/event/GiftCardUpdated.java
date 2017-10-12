package com.skytala.eCommerce.domain.accounting.relations.giftCard.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;
public class GiftCardUpdated implements Event{

	private boolean success;

	public GiftCardUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
