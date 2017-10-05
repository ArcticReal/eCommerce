package com.skytala.eCommerce.domain.paymentGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGroup.model.PaymentGroup;
public class PaymentGroupAdded implements Event{

	private PaymentGroup addedPaymentGroup;
	private boolean success;

	public PaymentGroupAdded(PaymentGroup addedPaymentGroup, boolean success){
		this.addedPaymentGroup = addedPaymentGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGroup getAddedPaymentGroup() {
		return addedPaymentGroup;
	}

}
