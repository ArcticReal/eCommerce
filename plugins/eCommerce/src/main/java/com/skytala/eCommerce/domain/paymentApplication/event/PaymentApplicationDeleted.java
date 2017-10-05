package com.skytala.eCommerce.domain.paymentApplication.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentApplication.model.PaymentApplication;
public class PaymentApplicationDeleted implements Event{

	private boolean success;

	public PaymentApplicationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
