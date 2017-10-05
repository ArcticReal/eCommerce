package com.skytala.eCommerce.domain.paymentMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentMethod.model.PaymentMethod;
public class PaymentMethodAdded implements Event{

	private PaymentMethod addedPaymentMethod;
	private boolean success;

	public PaymentMethodAdded(PaymentMethod addedPaymentMethod, boolean success){
		this.addedPaymentMethod = addedPaymentMethod;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentMethod getAddedPaymentMethod() {
		return addedPaymentMethod;
	}

}
