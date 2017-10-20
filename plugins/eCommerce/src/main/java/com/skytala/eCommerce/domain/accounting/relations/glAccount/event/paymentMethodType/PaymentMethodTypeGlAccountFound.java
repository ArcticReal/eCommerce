package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentMethodType.PaymentMethodTypeGlAccount;
public class PaymentMethodTypeGlAccountFound implements Event{

	private List<PaymentMethodTypeGlAccount> paymentMethodTypeGlAccounts;

	public PaymentMethodTypeGlAccountFound(List<PaymentMethodTypeGlAccount> paymentMethodTypeGlAccounts) {
		this.paymentMethodTypeGlAccounts = paymentMethodTypeGlAccounts;
	}

	public List<PaymentMethodTypeGlAccount> getPaymentMethodTypeGlAccounts()	{
		return paymentMethodTypeGlAccounts;
	}

}
