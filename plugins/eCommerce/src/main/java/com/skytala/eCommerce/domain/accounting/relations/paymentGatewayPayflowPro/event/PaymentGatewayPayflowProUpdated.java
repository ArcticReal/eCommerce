package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.model.PaymentGatewayPayflowPro;
public class PaymentGatewayPayflowProUpdated implements Event{

	private boolean success;

	public PaymentGatewayPayflowProUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
