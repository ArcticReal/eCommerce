package com.skytala.eCommerce.domain.accounting.relations.paymentApplication.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentApplication.model.PaymentApplication;
public class PaymentApplicationFound implements Event{

	private List<PaymentApplication> paymentApplications;

	public PaymentApplicationFound(List<PaymentApplication> paymentApplications) {
		this.paymentApplications = paymentApplications;
	}

	public List<PaymentApplication> getPaymentApplications()	{
		return paymentApplications;
	}

}
