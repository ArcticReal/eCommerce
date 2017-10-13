package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.model.PaymentGatewayResponse;
public class PaymentGatewayResponseUpdated implements Event{

	private boolean success;

	public PaymentGatewayResponseUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}