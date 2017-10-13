package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.model.TaxAuthorityGlAccount;
public class TaxAuthorityGlAccountAdded implements Event{

	private TaxAuthorityGlAccount addedTaxAuthorityGlAccount;
	private boolean success;

	public TaxAuthorityGlAccountAdded(TaxAuthorityGlAccount addedTaxAuthorityGlAccount, boolean success){
		this.addedTaxAuthorityGlAccount = addedTaxAuthorityGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TaxAuthorityGlAccount getAddedTaxAuthorityGlAccount() {
		return addedTaxAuthorityGlAccount;
	}

}