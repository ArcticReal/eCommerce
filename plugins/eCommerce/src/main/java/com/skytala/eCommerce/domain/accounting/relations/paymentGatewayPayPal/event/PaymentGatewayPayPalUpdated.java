package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.model.PaymentGatewayPayPal;
public class PaymentGatewayPayPalUpdated implements Event{

	private boolean success;

	public PaymentGatewayPayPalUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
