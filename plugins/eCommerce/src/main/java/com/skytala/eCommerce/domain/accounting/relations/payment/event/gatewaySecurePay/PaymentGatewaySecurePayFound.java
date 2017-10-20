package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySecurePay;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySecurePay.PaymentGatewaySecurePay;
public class PaymentGatewaySecurePayFound implements Event{

	private List<PaymentGatewaySecurePay> paymentGatewaySecurePays;

	public PaymentGatewaySecurePayFound(List<PaymentGatewaySecurePay> paymentGatewaySecurePays) {
		this.paymentGatewaySecurePays = paymentGatewaySecurePays;
	}

	public List<PaymentGatewaySecurePay> getPaymentGatewaySecurePays()	{
		return paymentGatewaySecurePays;
	}

}
