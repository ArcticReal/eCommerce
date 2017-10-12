package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.model.PaymentGatewayPayflowPro;
public class PaymentGatewayPayflowProDeleted implements Event{

	private boolean success;

	public PaymentGatewayPayflowProDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
