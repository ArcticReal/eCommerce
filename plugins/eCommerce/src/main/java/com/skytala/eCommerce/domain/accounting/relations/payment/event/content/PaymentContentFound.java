package com.skytala.eCommerce.domain.accounting.relations.payment.event.content;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.content.PaymentContent;
public class PaymentContentFound implements Event{

	private List<PaymentContent> paymentContents;

	public PaymentContentFound(List<PaymentContent> paymentContents) {
		this.paymentContents = paymentContents;
	}

	public List<PaymentContent> getPaymentContents()	{
		return paymentContents;
	}

}
