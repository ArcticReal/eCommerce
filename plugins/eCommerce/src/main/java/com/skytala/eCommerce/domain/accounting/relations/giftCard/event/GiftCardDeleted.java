package com.skytala.eCommerce.domain.accounting.relations.giftCard.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;
public class GiftCardDeleted implements Event{

	private boolean success;

	public GiftCardDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
