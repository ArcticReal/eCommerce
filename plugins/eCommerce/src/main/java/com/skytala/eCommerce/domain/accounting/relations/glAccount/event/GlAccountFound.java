package com.skytala.eCommerce.domain.accounting.relations.glAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.GlAccount;
public class GlAccountFound implements Event{

	private List<GlAccount> glAccounts;

	public GlAccountFound(List<GlAccount> glAccounts) {
		this.glAccounts = glAccounts;
	}

	public List<GlAccount> getGlAccounts()	{
		return glAccounts;
	}

}
