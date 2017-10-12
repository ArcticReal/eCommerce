package com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.model.CreditCardTypeGlAccount;
public class CreditCardTypeGlAccountUpdated implements Event{

	private boolean success;

	public CreditCardTypeGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
