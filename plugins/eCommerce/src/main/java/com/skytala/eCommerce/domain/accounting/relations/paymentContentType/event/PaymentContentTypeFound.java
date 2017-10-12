package com.skytala.eCommerce.domain.accounting.relations.paymentContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentContentType.model.PaymentContentType;
public class PaymentContentTypeFound implements Event{

	private List<PaymentContentType> paymentContentTypes;

	public PaymentContentTypeFound(List<PaymentContentType> paymentContentTypes) {
		this.paymentContentTypes = paymentContentTypes;
	}

	public List<PaymentContentType> getPaymentContentTypes()	{
		return paymentContentTypes;
	}

}
