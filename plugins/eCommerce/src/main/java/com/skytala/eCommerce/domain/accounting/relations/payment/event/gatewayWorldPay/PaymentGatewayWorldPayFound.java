package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;
public class PaymentGatewayWorldPayFound implements Event{

	private List<PaymentGatewayWorldPay> paymentGatewayWorldPays;

	public PaymentGatewayWorldPayFound(List<PaymentGatewayWorldPay> paymentGatewayWorldPays) {
		this.paymentGatewayWorldPays = paymentGatewayWorldPays;
	}

	public List<PaymentGatewayWorldPay> getPaymentGatewayWorldPays()	{
		return paymentGatewayWorldPays;
	}

}
