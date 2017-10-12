package com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.model.InvoiceTerm;
public class InvoiceTermUpdated implements Event{

	private boolean success;

	public InvoiceTermUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
