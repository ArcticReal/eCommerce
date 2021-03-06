package com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.term;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.term.BillingAccountTerm;
public class BillingAccountTermFound implements Event{

	private List<BillingAccountTerm> billingAccountTerms;

	public BillingAccountTermFound(List<BillingAccountTerm> billingAccountTerms) {
		this.billingAccountTerms = billingAccountTerms;
	}

	public List<BillingAccountTerm> getBillingAccountTerms()	{
		return billingAccountTerms;
	}

}
