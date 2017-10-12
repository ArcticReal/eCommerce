package com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.model.InvoiceItemType;
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
