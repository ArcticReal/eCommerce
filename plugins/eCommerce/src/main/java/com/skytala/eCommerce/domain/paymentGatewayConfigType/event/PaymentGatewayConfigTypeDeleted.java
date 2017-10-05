package com.skytala.eCommerce.domain.paymentGatewayConfigType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGatewayConfigType.model.PaymentGatewayConfigType;
public class PaymentGatewayConfigTypeDeleted implements Event{

	private boolean success;

	public PaymentGatewayConfigTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
