package com.skytala.eCommerce.domain.invoiceType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceType.model.InvoiceType;
public class InvoiceTypeFound implements Event{

	private List<InvoiceType> invoiceTypes;

	public InvoiceTypeFound(List<InvoiceType> invoiceTypes) {
		this.invoiceTypes = invoiceTypes;
	}

	public List<InvoiceType> getInvoiceTypes()	{
		return invoiceTypes;
	}

}
