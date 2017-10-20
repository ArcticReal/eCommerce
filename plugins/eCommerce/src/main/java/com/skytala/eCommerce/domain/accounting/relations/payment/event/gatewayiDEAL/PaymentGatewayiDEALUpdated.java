package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL.PaymentGatewayiDEAL;
public class PaymentGatewayiDEALUpdated implements Event{

	private boolean success;

	public PaymentGatewayiDEALUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
