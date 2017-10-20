package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemType.InvoiceItemType;
public class InvoiceItemTypeAdded implements Event{

	private InvoiceItemType addedInvoiceItemType;
	private boolean success;

	public InvoiceItemTypeAdded(InvoiceItemType addedInvoiceItemType, boolean success){
		this.addedInvoiceItemType = addedInvoiceItemType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItemType getAddedInvoiceItemType() {
		return addedInvoiceItemType;
	}

}
