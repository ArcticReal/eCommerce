package com.skytala.eCommerce.domain.accounting.relations.invoice.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.item.InvoiceItem;
public class InvoiceItemFound implements Event{

	private List<InvoiceItem> invoiceItems;

	public InvoiceItemFound(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public List<InvoiceItem> getInvoiceItems()	{
		return invoiceItems;
	}

}
