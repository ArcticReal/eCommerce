package com.skytala.eCommerce.domain.accounting.relations.billingAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.BillingAccount;
public class BillingAccountFound implements Event{

	private List<BillingAccount> billingAccounts;

	public BillingAccountFound(List<BillingAccount> billingAccounts) {
		this.billingAccounts = billingAccounts;
	}

	public List<BillingAccount> getBillingAccounts()	{
		return billingAccounts;
	}

}
