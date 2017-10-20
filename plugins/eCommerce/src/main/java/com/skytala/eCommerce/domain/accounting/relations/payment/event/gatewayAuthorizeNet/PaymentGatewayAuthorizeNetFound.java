package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;
public class PaymentGatewayAuthorizeNetFound implements Event{

	private List<PaymentGatewayAuthorizeNet> paymentGatewayAuthorizeNets;

	public PaymentGatewayAuthorizeNetFound(List<PaymentGatewayAuthorizeNet> paymentGatewayAuthorizeNets) {
		this.paymentGatewayAuthorizeNets = paymentGatewayAuthorizeNets;
	}

	public List<PaymentGatewayAuthorizeNet> getPaymentGatewayAuthorizeNets()	{
		return paymentGatewayAuthorizeNets;
	}

}
