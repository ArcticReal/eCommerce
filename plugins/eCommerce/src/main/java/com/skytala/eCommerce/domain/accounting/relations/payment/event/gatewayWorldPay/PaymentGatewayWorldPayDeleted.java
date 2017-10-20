package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;
public class PaymentGatewayWorldPayDeleted implements Event{

	private boolean success;

	public PaymentGatewayWorldPayDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
