package com.skytala.eCommerce.domain.accounting.relations.invoice.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;
public class InvoiceContentAdded implements Event{

	private InvoiceContent addedInvoiceContent;
	private boolean success;

	public InvoiceContentAdded(InvoiceContent addedInvoiceContent, boolean success){
		this.addedInvoiceContent = addedInvoiceContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceContent getAddedInvoiceContent() {
		return addedInvoiceContent;
	}

}
