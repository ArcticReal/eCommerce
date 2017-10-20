package com.skytala.eCommerce.domain.accounting.relations.invoice.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.role.InvoiceRole;
public class InvoiceRoleDeleted implements Event{

	private boolean success;

	public InvoiceRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
