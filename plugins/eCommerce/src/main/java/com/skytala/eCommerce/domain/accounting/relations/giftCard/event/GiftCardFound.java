package com.skytala.eCommerce.domain.accounting.relations.giftCard.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;
public class GiftCardFound implements Event{

	private List<GiftCard> giftCards;

	public GiftCardFound(List<GiftCard> giftCards) {
		this.giftCards = giftCards;
	}

	public List<GiftCard> getGiftCards()	{
		return giftCards;
	}

}
