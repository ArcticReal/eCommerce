package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;
public class PaymentGatewayWorldPayUpdated implements Event{

	private boolean success;

	public PaymentGatewayWorldPayUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
