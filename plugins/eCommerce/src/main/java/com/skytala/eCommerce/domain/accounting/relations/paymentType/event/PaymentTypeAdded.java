package com.skytala.eCommerce.domain.accounting.relations.paymentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentType.model.PaymentType;
public class PaymentTypeAdded implements Event{

	private PaymentType addedPaymentType;
	private boolean success;

	public PaymentTypeAdded(PaymentType addedPaymentType, boolean success){
		this.addedPaymentType = addedPaymentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentType getAddedPaymentType() {
		return addedPaymentType;
	}

}
