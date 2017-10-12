package com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.model.PaymentAttribute;
public class PaymentAttributeAdded implements Event{

	private PaymentAttribute addedPaymentAttribute;
	private boolean success;

	public PaymentAttributeAdded(PaymentAttribute addedPaymentAttribute, boolean success){
		this.addedPaymentAttribute = addedPaymentAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentAttribute getAddedPaymentAttribute() {
		return addedPaymentAttribute;
	}

}
