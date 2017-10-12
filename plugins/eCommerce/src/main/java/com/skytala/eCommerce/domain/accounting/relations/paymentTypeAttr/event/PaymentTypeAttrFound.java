package com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.model.PaymentTypeAttr;
public class PaymentTypeAttrFound implements Event{

	private List<PaymentTypeAttr> paymentTypeAttrs;

	public PaymentTypeAttrFound(List<PaymentTypeAttr> paymentTypeAttrs) {
		this.paymentTypeAttrs = paymentTypeAttrs;
	}

	public List<PaymentTypeAttr> getPaymentTypeAttrs()	{
		return paymentTypeAttrs;
	}

}
