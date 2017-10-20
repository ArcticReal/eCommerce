package com.skytala.eCommerce.domain.accounting.relations.invoice.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.role.InvoiceRole;
public class InvoiceRoleFound implements Event{

	private List<InvoiceRole> invoiceRoles;

	public InvoiceRoleFound(List<InvoiceRole> invoiceRoles) {
		this.invoiceRoles = invoiceRoles;
	}

	public List<InvoiceRole> getInvoiceRoles()	{
		return invoiceRoles;
	}

}
