package com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.model.InvoiceItemType;
public class InvoiceItemTypeUpdated implements Event{

	private boolean success;

	public InvoiceItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
