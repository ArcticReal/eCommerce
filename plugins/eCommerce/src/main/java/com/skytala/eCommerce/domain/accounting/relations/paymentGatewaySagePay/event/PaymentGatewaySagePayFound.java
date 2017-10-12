package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.model.PaymentGatewaySagePay;
public class PaymentGatewaySagePayFound implements Event{

	private List<PaymentGatewaySagePay> paymentGatewaySagePays;

	public PaymentGatewaySagePayFound(List<PaymentGatewaySagePay> paymentGatewaySagePays) {
		this.paymentGatewaySagePays = paymentGatewaySagePays;
	}

	public List<PaymentGatewaySagePay> getPaymentGatewaySagePays()	{
		return paymentGatewaySagePays;
	}

}
