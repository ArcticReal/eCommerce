package com.skytala.eCommerce.domain.accounting.relations.invoice.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.role.InvoiceRole;
public class InvoiceRoleAdded implements Event{

	private InvoiceRole addedInvoiceRole;
	private boolean success;

	public InvoiceRoleAdded(InvoiceRole addedInvoiceRole, boolean success){
		this.addedInvoiceRole = addedInvoiceRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceRole getAddedInvoiceRole() {
		return addedInvoiceRole;
	}

}
