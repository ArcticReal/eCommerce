package com.skytala.eCommerce.domain.payment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.payment.model.Payment;
public class PaymentAdded implements Event{

	private Payment addedPayment;
	private boolean success;

	public PaymentAdded(Payment addedPayment, boolean success){
		this.addedPayment = addedPayment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Payment getAddedPayment() {
		return addedPayment;
	}

}
