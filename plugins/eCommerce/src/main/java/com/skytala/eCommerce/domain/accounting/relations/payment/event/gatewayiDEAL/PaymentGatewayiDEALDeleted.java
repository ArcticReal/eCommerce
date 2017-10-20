package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL.PaymentGatewayiDEAL;
public class PaymentGatewayiDEALDeleted implements Event{

	private boolean success;

	public PaymentGatewayiDEALDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
