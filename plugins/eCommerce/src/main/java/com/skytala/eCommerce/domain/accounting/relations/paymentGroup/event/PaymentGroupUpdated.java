package com.skytala.eCommerce.domain.accounting.relations.paymentGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGroup.model.PaymentGroup;
public class PaymentGroupUpdated implements Event{

	private boolean success;

	public PaymentGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
