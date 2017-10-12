package com.skytala.eCommerce.domain.accounting.relations.checkAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;
public class CheckAccountFound implements Event{

	private List<CheckAccount> checkAccounts;

	public CheckAccountFound(List<CheckAccount> checkAccounts) {
		this.checkAccounts = checkAccounts;
	}

	public List<CheckAccount> getCheckAccounts()	{
		return checkAccounts;
	}

}
