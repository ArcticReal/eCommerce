package com.skytala.eCommerce.domain.invoiceItemAssocType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceItemAssocType.model.InvoiceItemAssocType;
public class InvoiceItemAssocTypeFound implements Event{

	private List<InvoiceItemAssocType> invoiceItemAssocTypes;

	public InvoiceItemAssocTypeFound(List<InvoiceItemAssocType> invoiceItemAssocTypes) {
		this.invoiceItemAssocTypes = invoiceItemAssocTypes;
	}

	public List<InvoiceItemAssocType> getInvoiceItemAssocTypes()	{
		return invoiceItemAssocTypes;
	}

}
