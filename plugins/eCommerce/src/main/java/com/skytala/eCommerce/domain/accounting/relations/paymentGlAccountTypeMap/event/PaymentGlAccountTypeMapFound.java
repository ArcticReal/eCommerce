package com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.model.PaymentGlAccountTypeMap;
public class PaymentGlAccountTypeMapFound implements Event{

	private List<PaymentGlAccountTypeMap> paymentGlAccountTypeMaps;

	public PaymentGlAccountTypeMapFound(List<PaymentGlAccountTypeMap> paymentGlAccountTypeMaps) {
		this.paymentGlAccountTypeMaps = paymentGlAccountTypeMaps;
	}

	public List<PaymentGlAccountTypeMap> getPaymentGlAccountTypeMaps()	{
		return paymentGlAccountTypeMaps;
	}

}
