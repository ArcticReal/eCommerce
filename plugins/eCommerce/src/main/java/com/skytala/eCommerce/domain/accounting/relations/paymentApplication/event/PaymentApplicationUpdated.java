package com.skytala.eCommerce.domain.accounting.relations.paymentApplication.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentApplication.model.PaymentApplication;
public class PaymentApplicationUpdated implements Event{

	private boolean success;

	public PaymentApplicationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
