package com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.typeAttr.PaymentTypeAttr;
public class PaymentTypeAttrDeleted implements Event{

	private boolean success;

	public PaymentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
