package com.skytala.eCommerce.domain.accounting.relations.finAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;
public class FinAccountFound implements Event{

	private List<FinAccount> finAccounts;

	public FinAccountFound(List<FinAccount> finAccounts) {
		this.finAccounts = finAccounts;
	}

	public List<FinAccount> getFinAccounts()	{
		return finAccounts;
	}

}
