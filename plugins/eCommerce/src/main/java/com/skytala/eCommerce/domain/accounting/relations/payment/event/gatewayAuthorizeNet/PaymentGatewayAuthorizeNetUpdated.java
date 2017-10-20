package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;
public class PaymentGatewayAuthorizeNetUpdated implements Event{

	private boolean success;

	public PaymentGatewayAuthorizeNetUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
