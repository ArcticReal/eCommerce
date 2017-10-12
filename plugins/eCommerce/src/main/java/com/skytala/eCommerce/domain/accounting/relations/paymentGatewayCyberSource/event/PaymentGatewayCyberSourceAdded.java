package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.model.PaymentGatewayCyberSource;
public class PaymentGatewayCyberSourceAdded implements Event{

	private PaymentGatewayCyberSource addedPaymentGatewayCyberSource;
	private boolean success;

	public PaymentGatewayCyberSourceAdded(PaymentGatewayCyberSource addedPaymentGatewayCyberSource, boolean success){
		this.addedPaymentGatewayCyberSource = addedPaymentGatewayCyberSource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayCyberSource getAddedPaymentGatewayCyberSource() {
		return addedPaymentGatewayCyberSource;
	}

}
