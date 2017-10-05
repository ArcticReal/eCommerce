package com.skytala.eCommerce.domain.invoice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoice.model.Invoice;
public class InvoiceAdded implements Event{

	private Invoice addedInvoice;
	private boolean success;

	public InvoiceAdded(Invoice addedInvoice, boolean success){
		this.addedInvoice = addedInvoice;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Invoice getAddedInvoice() {
		return addedInvoice;
	}

}
