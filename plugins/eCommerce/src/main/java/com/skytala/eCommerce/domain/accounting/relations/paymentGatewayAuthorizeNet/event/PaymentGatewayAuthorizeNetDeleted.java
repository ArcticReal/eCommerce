package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.model.PaymentGatewayAuthorizeNet;
public class PaymentGatewayAuthorizeNetDeleted implements Event{

	private boolean success;

	public PaymentGatewayAuthorizeNetDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
