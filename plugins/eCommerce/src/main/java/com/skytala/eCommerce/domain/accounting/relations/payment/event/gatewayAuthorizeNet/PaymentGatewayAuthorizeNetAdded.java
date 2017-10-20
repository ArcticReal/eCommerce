package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;
public class PaymentGatewayAuthorizeNetAdded implements Event{

	private PaymentGatewayAuthorizeNet addedPaymentGatewayAuthorizeNet;
	private boolean success;

	public PaymentGatewayAuthorizeNetAdded(PaymentGatewayAuthorizeNet addedPaymentGatewayAuthorizeNet, boolean success){
		this.addedPaymentGatewayAuthorizeNet = addedPaymentGatewayAuthorizeNet;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayAuthorizeNet getAddedPaymentGatewayAuthorizeNet() {
		return addedPaymentGatewayAuthorizeNet;
	}

}
