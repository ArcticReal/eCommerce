package com.skytala.eCommerce.domain.accounting.relations.invoiceContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceContent.model.InvoiceContent;
public class InvoiceContentUpdated implements Event{

	private boolean success;

	public InvoiceContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
