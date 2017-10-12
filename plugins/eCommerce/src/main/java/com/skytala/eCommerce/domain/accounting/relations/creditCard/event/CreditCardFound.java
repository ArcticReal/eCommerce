package com.skytala.eCommerce.domain.accounting.relations.creditCard.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;
public class CreditCardFound implements Event{

	private List<CreditCard> creditCards;

	public CreditCardFound(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<CreditCard> getCreditCards()	{
		return creditCards;
	}

}
