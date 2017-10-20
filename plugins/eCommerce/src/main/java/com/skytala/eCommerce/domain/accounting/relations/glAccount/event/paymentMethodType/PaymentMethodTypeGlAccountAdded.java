package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentMethodType.PaymentMethodTypeGlAccount;
public class PaymentMethodTypeGlAccountAdded implements Event{

	private PaymentMethodTypeGlAccount addedPaymentMethodTypeGlAccount;
	private boolean success;

	public PaymentMethodTypeGlAccountAdded(PaymentMethodTypeGlAccount addedPaymentMethodTypeGlAccount, boolean success){
		this.addedPaymentMethodTypeGlAccount = addedPaymentMethodTypeGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentMethodTypeGlAccount getAddedPaymentMethodTypeGlAccount() {
		return addedPaymentMethodTypeGlAccount;
	}

}
