package com.skytala.eCommerce.domain.accounting.relations.payment.event.application;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.application.PaymentApplication;
public class PaymentApplicationDeleted implements Event{

	private boolean success;

	public PaymentApplicationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
