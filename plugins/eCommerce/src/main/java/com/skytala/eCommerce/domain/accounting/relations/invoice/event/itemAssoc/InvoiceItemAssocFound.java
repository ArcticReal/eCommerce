package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssoc.InvoiceItemAssoc;
public class InvoiceItemAssocFound implements Event{

	private List<InvoiceItemAssoc> invoiceItemAssocs;

	public InvoiceItemAssocFound(List<InvoiceItemAssoc> invoiceItemAssocs) {
		this.invoiceItemAssocs = invoiceItemAssocs;
	}

	public List<InvoiceItemAssoc> getInvoiceItemAssocs()	{
		return invoiceItemAssocs;
	}

}
