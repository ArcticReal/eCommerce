package com.skytala.eCommerce.domain.accounting.relations.paymentContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentContent.model.PaymentContent;
public class PaymentContentFound implements Event{

	private List<PaymentContent> paymentContents;

	public PaymentContentFound(List<PaymentContent> paymentContents) {
		this.paymentContents = paymentContents;
	}

	public List<PaymentContent> getPaymentContents()	{
		return paymentContents;
	}

}
