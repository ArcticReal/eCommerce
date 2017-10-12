package com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.model.AcctgTransAttribute;
public class AcctgTransAttributeDeleted implements Event{

	private boolean success;

	public AcctgTransAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
