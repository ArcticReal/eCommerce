package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.varianceReason.VarianceReasonGlAccount;
public class VarianceReasonGlAccountDeleted implements Event{

	private boolean success;

	public VarianceReasonGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
