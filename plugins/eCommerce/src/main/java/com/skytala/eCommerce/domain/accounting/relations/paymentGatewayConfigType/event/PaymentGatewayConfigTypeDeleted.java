package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.model.PaymentGatewayConfigType;
public class PaymentGatewayConfigTypeDeleted implements Event{

	private boolean success;

	public PaymentGatewayConfigTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
