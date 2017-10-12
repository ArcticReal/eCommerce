package com.skytala.eCommerce.domain.accounting.relations.deduction.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.deduction.model.Deduction;
public class DeductionUpdated implements Event{

	private boolean success;

	public DeductionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
