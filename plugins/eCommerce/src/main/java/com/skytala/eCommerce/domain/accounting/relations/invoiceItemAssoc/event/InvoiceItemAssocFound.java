package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.model.InvoiceItemAssoc;
public class InvoiceItemAssocFound implements Event{

	private List<InvoiceItemAssoc> invoiceItemAssocs;

	public InvoiceItemAssocFound(List<InvoiceItemAssoc> invoiceItemAssocs) {
		this.invoiceItemAssocs = invoiceItemAssocs;
	}

	public List<InvoiceItemAssoc> getInvoiceItemAssocs()	{
		return invoiceItemAssocs;
	}

}
