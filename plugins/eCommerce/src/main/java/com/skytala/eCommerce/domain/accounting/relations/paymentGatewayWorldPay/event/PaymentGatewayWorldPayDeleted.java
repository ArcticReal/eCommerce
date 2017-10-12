package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.model.PaymentGatewayWorldPay;
public class PaymentGatewayWorldPayDeleted implements Event{

	private boolean success;

	public PaymentGatewayWorldPayDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
