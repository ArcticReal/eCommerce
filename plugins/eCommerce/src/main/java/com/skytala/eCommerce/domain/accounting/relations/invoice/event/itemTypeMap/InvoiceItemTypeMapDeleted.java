package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;
public class InvoiceItemTypeMapDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeMapDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
