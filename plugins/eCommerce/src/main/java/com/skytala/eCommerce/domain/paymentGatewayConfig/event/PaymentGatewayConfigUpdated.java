package com.skytala.eCommerce.domain.paymentGatewayConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGatewayConfig.model.PaymentGatewayConfig;
public class PaymentGatewayConfigUpdated implements Event{

	private boolean success;

	public PaymentGatewayConfigUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
