package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.model.PaymentGatewayCyberSource;
public class PaymentGatewayCyberSourceFound implements Event{

	private List<PaymentGatewayCyberSource> paymentGatewayCyberSources;

	public PaymentGatewayCyberSourceFound(List<PaymentGatewayCyberSource> paymentGatewayCyberSources) {
		this.paymentGatewayCyberSources = paymentGatewayCyberSources;
	}

	public List<PaymentGatewayCyberSource> getPaymentGatewayCyberSources()	{
		return paymentGatewayCyberSources;
	}

}
