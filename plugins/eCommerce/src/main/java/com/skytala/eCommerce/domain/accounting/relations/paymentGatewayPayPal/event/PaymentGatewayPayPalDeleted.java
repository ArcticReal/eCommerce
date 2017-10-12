package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.model.PaymentGatewayPayPal;
public class PaymentGatewayPayPalDeleted implements Event{

	private boolean success;

	public PaymentGatewayPayPalDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
