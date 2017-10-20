package com.skytala.eCommerce.domain.accounting.relations.invoice.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.item.InvoiceItem;
public class InvoiceItemAdded implements Event{

	private InvoiceItem addedInvoiceItem;
	private boolean success;

	public InvoiceItemAdded(InvoiceItem addedInvoiceItem, boolean success){
		this.addedInvoiceItem = addedInvoiceItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItem getAddedInvoiceItem() {
		return addedInvoiceItem;
	}

}
