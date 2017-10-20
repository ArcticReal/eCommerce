package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfig;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfig.PaymentGatewayConfig;
public class PaymentGatewayConfigDeleted implements Event{

	private boolean success;

	public PaymentGatewayConfigDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
