package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.model.PaymentGatewaySagePay;
public class PaymentGatewaySagePayDeleted implements Event{

	private boolean success;

	public PaymentGatewaySagePayDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
