package com.skytala.eCommerce.domain.accounting.relations.payment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;
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
