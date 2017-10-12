package com.skytala.eCommerce.domain.accounting.relations.payment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.Payment;
public class PaymentDeleted implements Event{

	private boolean success;

	public PaymentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
