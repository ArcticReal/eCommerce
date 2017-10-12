package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.model.InvoiceItemTypeMap;
public class InvoiceItemTypeMapDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeMapDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
