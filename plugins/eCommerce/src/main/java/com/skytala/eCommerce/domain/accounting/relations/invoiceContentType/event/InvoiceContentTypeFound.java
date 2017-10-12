package com.skytala.eCommerce.domain.accounting.relations.invoiceContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceContentType.model.InvoiceContentType;
public class InvoiceContentTypeFound implements Event{

	private List<InvoiceContentType> invoiceContentTypes;

	public InvoiceContentTypeFound(List<InvoiceContentType> invoiceContentTypes) {
		this.invoiceContentTypes = invoiceContentTypes;
	}

	public List<InvoiceContentType> getInvoiceContentTypes()	{
		return invoiceContentTypes;
	}

}
