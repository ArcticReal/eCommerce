package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayResponse;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayResponse.PaymentGatewayResponse;
public class PaymentGatewayResponseDeleted implements Event{

	private boolean success;

	public PaymentGatewayResponseDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
