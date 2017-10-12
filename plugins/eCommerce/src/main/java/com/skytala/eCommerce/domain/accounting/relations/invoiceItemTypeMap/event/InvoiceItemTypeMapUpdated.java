package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.model.InvoiceItemTypeMap;
public class InvoiceItemTypeMapUpdated implements Event{

	private boolean success;

	public InvoiceItemTypeMapUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
