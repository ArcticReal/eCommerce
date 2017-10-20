package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySecurePay;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySecurePay.PaymentGatewaySecurePay;
public class PaymentGatewaySecurePayDeleted implements Event{

	private boolean success;

	public PaymentGatewaySecurePayDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
