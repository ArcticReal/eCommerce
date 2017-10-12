package com.skytala.eCommerce.domain.accounting.relations.invoiceItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItem.model.InvoiceItem;
public class InvoiceItemUpdated implements Event{

	private boolean success;

	public InvoiceItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
