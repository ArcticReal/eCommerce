package com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.model.PaymentTypeAttr;
public class PaymentTypeAttrDeleted implements Event{

	private boolean success;

	public PaymentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
