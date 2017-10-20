package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayClearCommerce;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayClearCommerce.PaymentGatewayClearCommerce;
public class PaymentGatewayClearCommerceAdded implements Event{

	private PaymentGatewayClearCommerce addedPaymentGatewayClearCommerce;
	private boolean success;

	public PaymentGatewayClearCommerceAdded(PaymentGatewayClearCommerce addedPaymentGatewayClearCommerce, boolean success){
		this.addedPaymentGatewayClearCommerce = addedPaymentGatewayClearCommerce;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayClearCommerce getAddedPaymentGatewayClearCommerce() {
		return addedPaymentGatewayClearCommerce;
	}

}
