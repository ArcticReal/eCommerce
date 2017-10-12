package com.skytala.eCommerce.domain.accounting.relations.invoiceRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.model.InvoiceRole;
public class InvoiceRoleUpdated implements Event{

	private boolean success;

	public InvoiceRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
