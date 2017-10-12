package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.model.PaymentGatewayAuthorizeNet;
public class PaymentGatewayAuthorizeNetFound implements Event{

	private List<PaymentGatewayAuthorizeNet> paymentGatewayAuthorizeNets;

	public PaymentGatewayAuthorizeNetFound(List<PaymentGatewayAuthorizeNet> paymentGatewayAuthorizeNets) {
		this.paymentGatewayAuthorizeNets = paymentGatewayAuthorizeNets;
	}

	public List<PaymentGatewayAuthorizeNet> getPaymentGatewayAuthorizeNets()	{
		return paymentGatewayAuthorizeNets;
	}

}
