package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.model.PaymentGatewaySagePay;
public class PaymentGatewaySagePayAdded implements Event{

	private PaymentGatewaySagePay addedPaymentGatewaySagePay;
	private boolean success;

	public PaymentGatewaySagePayAdded(PaymentGatewaySagePay addedPaymentGatewaySagePay, boolean success){
		this.addedPaymentGatewaySagePay = addedPaymentGatewaySagePay;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewaySagePay getAddedPaymentGatewaySagePay() {
		return addedPaymentGatewaySagePay;
	}

}
