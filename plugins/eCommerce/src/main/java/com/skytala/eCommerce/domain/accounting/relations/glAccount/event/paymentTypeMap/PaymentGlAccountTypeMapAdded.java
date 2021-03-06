package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap.PaymentGlAccountTypeMap;
public class PaymentGlAccountTypeMapAdded implements Event{

	private PaymentGlAccountTypeMap addedPaymentGlAccountTypeMap;
	private boolean success;

	public PaymentGlAccountTypeMapAdded(PaymentGlAccountTypeMap addedPaymentGlAccountTypeMap, boolean success){
		this.addedPaymentGlAccountTypeMap = addedPaymentGlAccountTypeMap;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGlAccountTypeMap getAddedPaymentGlAccountTypeMap() {
		return addedPaymentGlAccountTypeMap;
	}

}
