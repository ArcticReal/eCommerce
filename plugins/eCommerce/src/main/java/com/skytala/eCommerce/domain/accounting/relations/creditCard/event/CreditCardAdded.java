package com.skytala.eCommerce.domain.accounting.relations.creditCard.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;
public class CreditCardAdded implements Event{

	private CreditCard addedCreditCard;
	private boolean success;

	public CreditCardAdded(CreditCard addedCreditCard, boolean success){
		this.addedCreditCard = addedCreditCard;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CreditCard getAddedCreditCard() {
		return addedCreditCard;
	}

}
