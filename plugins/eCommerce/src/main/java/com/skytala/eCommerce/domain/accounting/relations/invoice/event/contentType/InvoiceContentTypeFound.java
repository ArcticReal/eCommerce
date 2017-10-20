package com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType.InvoiceContentType;
public class InvoiceContentTypeFound implements Event{

	private List<InvoiceContentType> invoiceContentTypes;

	public InvoiceContentTypeFound(List<InvoiceContentType> invoiceContentTypes) {
		this.invoiceContentTypes = invoiceContentTypes;
	}

	public List<InvoiceContentType> getInvoiceContentTypes()	{
		return invoiceContentTypes;
	}

}
