package com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.model.CreditCardTypeGlAccount;
public class CreditCardTypeGlAccountDeleted implements Event{

	private boolean success;

	public CreditCardTypeGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
