package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfigType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfigType.PaymentGatewayConfigType;
public class PaymentGatewayConfigTypeUpdated implements Event{

	private boolean success;

	public PaymentGatewayConfigTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
