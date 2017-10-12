package com.skytala.eCommerce.domain.accounting.relations.deduction.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.deduction.model.Deduction;
public class DeductionDeleted implements Event{

	private boolean success;

	public DeductionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
