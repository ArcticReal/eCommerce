package com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contactMech.InvoiceContactMech;
public class InvoiceContactMechFound implements Event{

	private List<InvoiceContactMech> invoiceContactMechs;

	public InvoiceContactMechFound(List<InvoiceContactMech> invoiceContactMechs) {
		this.invoiceContactMechs = invoiceContactMechs;
	}

	public List<InvoiceContactMech> getInvoiceContactMechs()	{
		return invoiceContactMechs;
	}

}
