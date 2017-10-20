package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.varianceReason.VarianceReasonGlAccount;
public class VarianceReasonGlAccountUpdated implements Event{

	private boolean success;

	public VarianceReasonGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
