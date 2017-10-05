package com.skytala.eCommerce.domain.paymentContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentContentType.model.PaymentContentType;
public class PaymentContentTypeAdded implements Event{

	private PaymentContentType addedPaymentContentType;
	private boolean success;

	public PaymentContentTypeAdded(PaymentContentType addedPaymentContentType, boolean success){
		this.addedPaymentContentType = addedPaymentContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentContentType getAddedPaymentContentType() {
		return addedPaymentContentType;
	}

}
