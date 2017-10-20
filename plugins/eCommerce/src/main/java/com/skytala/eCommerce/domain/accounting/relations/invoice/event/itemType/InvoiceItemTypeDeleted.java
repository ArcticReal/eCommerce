package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemType.InvoiceItemType;
public class InvoiceItemTypeDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
