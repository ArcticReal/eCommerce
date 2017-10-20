package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayflowPro.PaymentGatewayPayflowPro;
public class PaymentGatewayPayflowProDeleted implements Event{

	private boolean success;

	public PaymentGatewayPayflowProDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
