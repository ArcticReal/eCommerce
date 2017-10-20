package com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.attribute.InvoiceAttribute;
public class InvoiceAttributeFound implements Event{

	private List<InvoiceAttribute> invoiceAttributes;

	public InvoiceAttributeFound(List<InvoiceAttribute> invoiceAttributes) {
		this.invoiceAttributes = invoiceAttributes;
	}

	public List<InvoiceAttribute> getInvoiceAttributes()	{
		return invoiceAttributes;
	}

}
