package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayEway;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayEway.PaymentGatewayEway;
public class PaymentGatewayEwayFound implements Event{

	private List<PaymentGatewayEway> paymentGatewayEways;

	public PaymentGatewayEwayFound(List<PaymentGatewayEway> paymentGatewayEways) {
		this.paymentGatewayEways = paymentGatewayEways;
	}

	public List<PaymentGatewayEway> getPaymentGatewayEways()	{
		return paymentGatewayEways;
	}

}
