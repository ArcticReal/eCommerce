package com.skytala.eCommerce.domain.accounting.relations.invoice.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.Invoice;
public class InvoiceFound implements Event{

	private List<Invoice> invoices;

	public InvoiceFound(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public List<Invoice> getInvoices()	{
		return invoices;
	}

}
