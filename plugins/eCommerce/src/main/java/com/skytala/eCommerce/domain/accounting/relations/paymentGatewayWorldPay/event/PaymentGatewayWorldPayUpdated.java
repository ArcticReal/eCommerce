package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.model.PaymentGatewayWorldPay;
public class PaymentGatewayWorldPayUpdated implements Event{

	private boolean success;

	public PaymentGatewayWorldPayUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
