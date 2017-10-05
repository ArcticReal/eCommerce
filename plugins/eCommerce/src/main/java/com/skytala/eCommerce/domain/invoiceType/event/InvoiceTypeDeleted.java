package com.skytala.eCommerce.domain.invoiceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceType.model.InvoiceType;
public class InvoiceTypeDeleted implements Event{

	private boolean success;

	public InvoiceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
