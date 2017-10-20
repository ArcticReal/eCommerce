package com.skytala.eCommerce.domain.accounting.relations.invoice.event.content;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;
public class InvoiceContentFound implements Event{

	private List<InvoiceContent> invoiceContents;

	public InvoiceContentFound(List<InvoiceContent> invoiceContents) {
		this.invoiceContents = invoiceContents;
	}

	public List<InvoiceContent> getInvoiceContents()	{
		return invoiceContents;
	}

}
