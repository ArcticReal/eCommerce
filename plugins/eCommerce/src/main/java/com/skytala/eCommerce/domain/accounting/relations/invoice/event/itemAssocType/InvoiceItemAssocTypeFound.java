package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssocType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssocType.InvoiceItemAssocType;
public class InvoiceItemAssocTypeFound implements Event{

	private List<InvoiceItemAssocType> invoiceItemAssocTypes;

	public InvoiceItemAssocTypeFound(List<InvoiceItemAssocType> invoiceItemAssocTypes) {
		this.invoiceItemAssocTypes = invoiceItemAssocTypes;
	}

	public List<InvoiceItemAssocType> getInvoiceItemAssocTypes()	{
		return invoiceItemAssocTypes;
	}

}
