package com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.model.InvoiceStatus;
public class InvoiceStatusAdded implements Event{

	private InvoiceStatus addedInvoiceStatus;
	private boolean success;

	public InvoiceStatusAdded(InvoiceStatus addedInvoiceStatus, boolean success){
		this.addedInvoiceStatus = addedInvoiceStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceStatus getAddedInvoiceStatus() {
		return addedInvoiceStatus;
	}

}