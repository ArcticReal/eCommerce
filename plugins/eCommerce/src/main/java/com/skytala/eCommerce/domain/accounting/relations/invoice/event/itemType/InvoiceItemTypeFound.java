package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemType.InvoiceItemType;
public class InvoiceItemTypeFound implements Event{

	private List<InvoiceItemType> invoiceItemTypes;

	public InvoiceItemTypeFound(List<InvoiceItemType> invoiceItemTypes) {
		this.invoiceItemTypes = invoiceItemTypes;
	}

	public List<InvoiceItemType> getInvoiceItemTypes()	{
		return invoiceItemTypes;
	}

}
