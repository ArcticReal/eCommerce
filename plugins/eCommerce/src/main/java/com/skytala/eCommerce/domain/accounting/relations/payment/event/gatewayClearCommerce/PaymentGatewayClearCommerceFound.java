package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayClearCommerce;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayClearCommerce.PaymentGatewayClearCommerce;
public class PaymentGatewayClearCommerceFound implements Event{

	private List<PaymentGatewayClearCommerce> paymentGatewayClearCommerces;

	public PaymentGatewayClearCommerceFound(List<PaymentGatewayClearCommerce> paymentGatewayClearCommerces) {
		this.paymentGatewayClearCommerces = paymentGatewayClearCommerces;
	}

	public List<PaymentGatewayClearCommerce> getPaymentGatewayClearCommerces()	{
		return paymentGatewayClearCommerces;
	}

}
