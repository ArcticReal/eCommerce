package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap.PaymentGlAccountTypeMap;
public class PaymentGlAccountTypeMapDeleted implements Event{

	private boolean success;

	public PaymentGlAccountTypeMapDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
