package com.skytala.eCommerce.domain.accounting.relations.deduction.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.deduction.model.type.DeductionType;
public class DeductionTypeUpdated implements Event{

	private boolean success;

	public DeductionTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
