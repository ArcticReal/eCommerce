package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.model.PaymentGatewayiDEAL;
public class PaymentGatewayiDEALUpdated implements Event{

	private boolean success;

	public PaymentGatewayiDEALUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
