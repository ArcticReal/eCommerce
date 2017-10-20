package com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType.InvoiceContentType;
public class InvoiceContentTypeAdded implements Event{

	private InvoiceContentType addedInvoiceContentType;
	private boolean success;

	public InvoiceContentTypeAdded(InvoiceContentType addedInvoiceContentType, boolean success){
		this.addedInvoiceContentType = addedInvoiceContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceContentType getAddedInvoiceContentType() {
		return addedInvoiceContentType;
	}

}
