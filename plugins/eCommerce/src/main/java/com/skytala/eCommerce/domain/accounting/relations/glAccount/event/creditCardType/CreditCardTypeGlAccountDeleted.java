package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.creditCardType.CreditCardTypeGlAccount;
public class CreditCardTypeGlAccountDeleted implements Event{

	private boolean success;

	public CreditCardTypeGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
