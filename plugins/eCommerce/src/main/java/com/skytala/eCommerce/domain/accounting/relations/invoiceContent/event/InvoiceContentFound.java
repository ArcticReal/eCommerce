package com.skytala.eCommerce.domain.accounting.relations.invoiceContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceContent.model.InvoiceContent;
public class InvoiceContentFound implements Event{

	private List<InvoiceContent> invoiceContents;

	public InvoiceContentFound(List<InvoiceContent> invoiceContents) {
		this.invoiceContents = invoiceContents;
	}

	public List<InvoiceContent> getInvoiceContents()	{
		return invoiceContents;
	}

}
