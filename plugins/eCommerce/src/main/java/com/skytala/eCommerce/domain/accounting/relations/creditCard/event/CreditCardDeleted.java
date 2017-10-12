package com.skytala.eCommerce.domain.accounting.relations.creditCard.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;
public class CreditCardDeleted implements Event{

	private boolean success;

	public CreditCardDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
