package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.model.PaymentGatewaySecurePay;
public class PaymentGatewaySecurePayUpdated implements Event{

	private boolean success;

	public PaymentGatewaySecurePayUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
