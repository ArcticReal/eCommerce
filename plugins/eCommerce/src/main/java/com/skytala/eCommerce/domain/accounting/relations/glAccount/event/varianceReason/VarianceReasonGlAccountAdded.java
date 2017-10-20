package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.varianceReason.VarianceReasonGlAccount;
public class VarianceReasonGlAccountAdded implements Event{

	private VarianceReasonGlAccount addedVarianceReasonGlAccount;
	private boolean success;

	public VarianceReasonGlAccountAdded(VarianceReasonGlAccount addedVarianceReasonGlAccount, boolean success){
		this.addedVarianceReasonGlAccount = addedVarianceReasonGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public VarianceReasonGlAccount getAddedVarianceReasonGlAccount() {
		return addedVarianceReasonGlAccount;
	}

}
