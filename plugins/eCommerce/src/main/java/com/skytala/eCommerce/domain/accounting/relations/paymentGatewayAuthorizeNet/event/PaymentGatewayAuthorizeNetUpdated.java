package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.model.PaymentGatewayAuthorizeNet;
public class PaymentGatewayAuthorizeNetUpdated implements Event{

	private boolean success;

	public PaymentGatewayAuthorizeNetUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
