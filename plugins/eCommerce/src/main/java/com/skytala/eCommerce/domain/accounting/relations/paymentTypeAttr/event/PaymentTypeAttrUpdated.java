package com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.model.PaymentTypeAttr;
public class PaymentTypeAttrUpdated implements Event{

	private boolean success;

	public PaymentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
