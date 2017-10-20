package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;
public class InvoiceItemTypeMapUpdated implements Event{

	private boolean success;

	public InvoiceItemTypeMapUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
