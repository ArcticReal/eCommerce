package com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.model.AcctgTransAttribute;
public class AcctgTransAttributeUpdated implements Event{

	private boolean success;

	public AcctgTransAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
