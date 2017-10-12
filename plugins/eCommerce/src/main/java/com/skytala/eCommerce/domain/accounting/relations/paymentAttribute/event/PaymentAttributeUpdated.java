package com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.model.PaymentAttribute;
public class PaymentAttributeUpdated implements Event{

	private boolean success;

	public PaymentAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
