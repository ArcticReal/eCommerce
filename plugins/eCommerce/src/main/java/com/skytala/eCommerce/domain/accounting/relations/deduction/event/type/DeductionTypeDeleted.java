package com.skytala.eCommerce.domain.accounting.relations.deduction.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.deduction.model.type.DeductionType;
public class DeductionTypeDeleted implements Event{

	private boolean success;

	public DeductionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
