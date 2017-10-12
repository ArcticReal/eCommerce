package com.skytala.eCommerce.domain.accounting.relations.giftCard.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;
public class GiftCardAdded implements Event{

	private GiftCard addedGiftCard;
	private boolean success;

	public GiftCardAdded(GiftCard addedGiftCard, boolean success){
		this.addedGiftCard = addedGiftCard;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GiftCard getAddedGiftCard() {
		return addedGiftCard;
	}

}
