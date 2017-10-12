package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.model.PaymentGatewayConfigType;
public class PaymentGatewayConfigTypeUpdated implements Event{

	private boolean success;

	public PaymentGatewayConfigTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
