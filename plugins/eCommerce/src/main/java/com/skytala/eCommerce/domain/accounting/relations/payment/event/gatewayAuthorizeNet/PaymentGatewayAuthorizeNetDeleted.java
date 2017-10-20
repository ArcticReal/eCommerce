package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;
public class PaymentGatewayAuthorizeNetDeleted implements Event{

	private boolean success;

	public PaymentGatewayAuthorizeNetDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
