package com.skytala.eCommerce.domain.accounting.relations.eftAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;
public class EftAccountFound implements Event{

	private List<EftAccount> eftAccounts;

	public EftAccountFound(List<EftAccount> eftAccounts) {
		this.eftAccounts = eftAccounts;
	}

	public List<EftAccount> getEftAccounts()	{
		return eftAccounts;
	}

}
