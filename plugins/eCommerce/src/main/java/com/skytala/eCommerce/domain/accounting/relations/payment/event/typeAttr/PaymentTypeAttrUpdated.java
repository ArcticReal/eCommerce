package com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.typeAttr.PaymentTypeAttr;
public class PaymentTypeAttrUpdated implements Event{

	private boolean success;

	public PaymentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
