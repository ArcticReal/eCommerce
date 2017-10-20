package com.skytala.eCommerce.domain.accounting.relations.payment.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.content.PaymentContent;
public class PaymentContentAdded implements Event{

	private PaymentContent addedPaymentContent;
	private boolean success;

	public PaymentContentAdded(PaymentContent addedPaymentContent, boolean success){
		this.addedPaymentContent = addedPaymentContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentContent getAddedPaymentContent() {
		return addedPaymentContent;
	}

}
