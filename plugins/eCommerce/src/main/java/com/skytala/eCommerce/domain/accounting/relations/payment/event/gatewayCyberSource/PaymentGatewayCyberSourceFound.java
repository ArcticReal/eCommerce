package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;
public class PaymentGatewayCyberSourceFound implements Event{

	private List<PaymentGatewayCyberSource> paymentGatewayCyberSources;

	public PaymentGatewayCyberSourceFound(List<PaymentGatewayCyberSource> paymentGatewayCyberSources) {
		this.paymentGatewayCyberSources = paymentGatewayCyberSources;
	}

	public List<PaymentGatewayCyberSource> getPaymentGatewayCyberSources()	{
		return paymentGatewayCyberSources;
	}

}
