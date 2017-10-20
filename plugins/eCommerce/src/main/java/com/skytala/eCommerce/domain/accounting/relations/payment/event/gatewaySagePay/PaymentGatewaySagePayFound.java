package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySagePay.PaymentGatewaySagePay;
public class PaymentGatewaySagePayFound implements Event{

	private List<PaymentGatewaySagePay> paymentGatewaySagePays;

	public PaymentGatewaySagePayFound(List<PaymentGatewaySagePay> paymentGatewaySagePays) {
		this.paymentGatewaySagePays = paymentGatewaySagePays;
	}

	public List<PaymentGatewaySagePay> getPaymentGatewaySagePays()	{
		return paymentGatewaySagePays;
	}

}
