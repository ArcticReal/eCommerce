package com.skytala.eCommerce.domain.invoiceItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceItemType.model.InvoiceItemType;
public class InvoiceItemTypeDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
