package com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.model.CreditCardTypeGlAccount;
public class CreditCardTypeGlAccountAdded implements Event{

	private CreditCardTypeGlAccount addedCreditCardTypeGlAccount;
	private boolean success;

	public CreditCardTypeGlAccountAdded(CreditCardTypeGlAccount addedCreditCardTypeGlAccount, boolean success){
		this.addedCreditCardTypeGlAccount = addedCreditCardTypeGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CreditCardTypeGlAccount getAddedCreditCardTypeGlAccount() {
		return addedCreditCardTypeGlAccount;
	}

}
