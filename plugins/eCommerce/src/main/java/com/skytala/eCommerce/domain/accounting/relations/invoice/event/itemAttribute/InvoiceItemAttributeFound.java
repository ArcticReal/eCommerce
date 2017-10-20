package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;
public class InvoiceItemAttributeFound implements Event{

	private List<InvoiceItemAttribute> invoiceItemAttributes;

	public InvoiceItemAttributeFound(List<InvoiceItemAttribute> invoiceItemAttributes) {
		this.invoiceItemAttributes = invoiceItemAttributes;
	}

	public List<InvoiceItemAttribute> getInvoiceItemAttributes()	{
		return invoiceItemAttributes;
	}

}
