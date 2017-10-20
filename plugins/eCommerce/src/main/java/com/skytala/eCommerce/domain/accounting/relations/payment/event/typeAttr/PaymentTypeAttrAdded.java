package com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.typeAttr.PaymentTypeAttr;
public class PaymentTypeAttrAdded implements Event{

	private PaymentTypeAttr addedPaymentTypeAttr;
	private boolean success;

	public PaymentTypeAttrAdded(PaymentTypeAttr addedPaymentTypeAttr, boolean success){
		this.addedPaymentTypeAttr = addedPaymentTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentTypeAttr getAddedPaymentTypeAttr() {
		return addedPaymentTypeAttr;
	}

}
