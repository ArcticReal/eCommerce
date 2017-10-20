package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.varianceReason.VarianceReasonGlAccount;
public class VarianceReasonGlAccountFound implements Event{

	private List<VarianceReasonGlAccount> varianceReasonGlAccounts;

	public VarianceReasonGlAccountFound(List<VarianceReasonGlAccount> varianceReasonGlAccounts) {
		this.varianceReasonGlAccounts = varianceReasonGlAccounts;
	}

	public List<VarianceReasonGlAccount> getVarianceReasonGlAccounts()	{
		return varianceReasonGlAccounts;
	}

}
