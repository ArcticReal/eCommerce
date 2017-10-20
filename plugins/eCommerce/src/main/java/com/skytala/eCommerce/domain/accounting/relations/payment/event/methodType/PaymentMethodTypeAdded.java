package com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.methodType.PaymentMethodType;
public class PaymentMethodTypeAdded implements Event{

	private PaymentMethodType addedPaymentMethodType;
	private boolean success;

	public PaymentMethodTypeAdded(PaymentMethodType addedPaymentMethodType, boolean success){
		this.addedPaymentMethodType = addedPaymentMethodType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentMethodType getAddedPaymentMethodType() {
		return addedPaymentMethodType;
	}

}
