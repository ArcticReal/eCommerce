package com.skytala.eCommerce.domain.accounting.relations.invoice.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.type.InvoiceType;
public class InvoiceTypeFound implements Event{

	private List<InvoiceType> invoiceTypes;

	public InvoiceTypeFound(List<InvoiceType> invoiceTypes) {
		this.invoiceTypes = invoiceTypes;
	}

	public List<InvoiceType> getInvoiceTypes()	{
		return invoiceTypes;
	}

}
