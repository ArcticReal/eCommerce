package com.skytala.eCommerce.domain.accounting.relations.paymentApplication.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentApplication.model.PaymentApplication;
public class PaymentApplicationAdded implements Event{

	private PaymentApplication addedPaymentApplication;
	private boolean success;

	public PaymentApplicationAdded(PaymentApplication addedPaymentApplication, boolean success){
		this.addedPaymentApplication = addedPaymentApplication;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentApplication getAddedPaymentApplication() {
		return addedPaymentApplication;
	}

}
