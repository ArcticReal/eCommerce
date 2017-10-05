package com.skytala.eCommerce.domain.invoiceItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceItemType.model.InvoiceItemType;
public class InvoiceItemTypeUpdated implements Event{

	private boolean success;

	public InvoiceItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
