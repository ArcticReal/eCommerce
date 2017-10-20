package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySagePay.PaymentGatewaySagePay;
public class PaymentGatewaySagePayUpdated implements Event{

	private boolean success;

	public PaymentGatewaySagePayUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
