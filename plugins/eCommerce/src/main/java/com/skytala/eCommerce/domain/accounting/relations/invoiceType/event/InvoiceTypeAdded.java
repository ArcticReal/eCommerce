package com.skytala.eCommerce.domain.accounting.relations.invoiceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceType.model.InvoiceType;
public class InvoiceTypeAdded implements Event{

	private InvoiceType addedInvoiceType;
	private boolean success;

	public InvoiceTypeAdded(InvoiceType addedInvoiceType, boolean success){
		this.addedInvoiceType = addedInvoiceType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceType getAddedInvoiceType() {
		return addedInvoiceType;
	}

}
