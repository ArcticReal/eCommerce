package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.model.PaymentGatewayPayPal;
public class PaymentGatewayPayPalFound implements Event{

	private List<PaymentGatewayPayPal> paymentGatewayPayPals;

	public PaymentGatewayPayPalFound(List<PaymentGatewayPayPal> paymentGatewayPayPals) {
		this.paymentGatewayPayPals = paymentGatewayPayPals;
	}

	public List<PaymentGatewayPayPal> getPaymentGatewayPayPals()	{
		return paymentGatewayPayPals;
	}

}
