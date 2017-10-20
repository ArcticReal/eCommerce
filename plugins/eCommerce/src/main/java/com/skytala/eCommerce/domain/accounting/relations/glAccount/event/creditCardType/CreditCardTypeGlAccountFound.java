package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.creditCardType.CreditCardTypeGlAccount;
public class CreditCardTypeGlAccountFound implements Event{

	private List<CreditCardTypeGlAccount> creditCardTypeGlAccounts;

	public CreditCardTypeGlAccountFound(List<CreditCardTypeGlAccount> creditCardTypeGlAccounts) {
		this.creditCardTypeGlAccounts = creditCardTypeGlAccounts;
	}

	public List<CreditCardTypeGlAccount> getCreditCardTypeGlAccounts()	{
		return creditCardTypeGlAccounts;
	}

}
