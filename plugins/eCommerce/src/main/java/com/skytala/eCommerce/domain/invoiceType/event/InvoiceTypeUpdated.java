package com.skytala.eCommerce.domain.invoiceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceType.model.InvoiceType;
public class InvoiceTypeUpdated implements Event{

	private boolean success;

	public InvoiceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
