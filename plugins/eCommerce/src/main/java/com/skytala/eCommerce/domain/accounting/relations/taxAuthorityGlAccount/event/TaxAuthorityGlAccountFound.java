package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.model.TaxAuthorityGlAccount;
public class TaxAuthorityGlAccountFound implements Event{

	private List<TaxAuthorityGlAccount> taxAuthorityGlAccounts;

	public TaxAuthorityGlAccountFound(List<TaxAuthorityGlAccount> taxAuthorityGlAccounts) {
		this.taxAuthorityGlAccounts = taxAuthorityGlAccounts;
	}

	public List<TaxAuthorityGlAccount> getTaxAuthorityGlAccounts()	{
		return taxAuthorityGlAccounts;
	}

}
