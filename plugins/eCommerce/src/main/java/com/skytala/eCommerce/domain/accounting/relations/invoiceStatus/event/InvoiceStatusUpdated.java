package com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.model.InvoiceStatus;
public class InvoiceStatusUpdated implements Event{

	private boolean success;

	public InvoiceStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
