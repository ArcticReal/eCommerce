package com.skytala.eCommerce.domain.accounting.relations.invoiceRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.model.InvoiceRole;
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
