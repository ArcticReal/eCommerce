package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeGlAccount.FinAccountTypeGlAccount;
public class FinAccountTypeGlAccountFound implements Event{

	private List<FinAccountTypeGlAccount> finAccountTypeGlAccounts;

	public FinAccountTypeGlAccountFound(List<FinAccountTypeGlAccount> finAccountTypeGlAccounts) {
		this.finAccountTypeGlAccounts = finAccountTypeGlAccounts;
	}

	public List<FinAccountTypeGlAccount> getFinAccountTypeGlAccounts()	{
		return finAccountTypeGlAccounts;
	}

}
