package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;
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
