package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.model.PaymentGatewayEway;
public class PaymentGatewayEwayUpdated implements Event{

	private boolean success;

	public PaymentGatewayEwayUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
