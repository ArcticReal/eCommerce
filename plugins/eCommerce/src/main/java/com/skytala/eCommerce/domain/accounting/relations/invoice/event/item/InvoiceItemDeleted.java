package com.skytala.eCommerce.domain.accounting.relations.invoice.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.item.InvoiceItem;
public class InvoiceItemDeleted implements Event{

	private boolean success;

	public InvoiceItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
