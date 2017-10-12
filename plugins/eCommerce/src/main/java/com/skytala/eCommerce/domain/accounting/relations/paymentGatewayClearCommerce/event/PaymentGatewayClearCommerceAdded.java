package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.model.PaymentGatewayClearCommerce;
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
