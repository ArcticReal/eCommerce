package com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.model.InvoiceAttribute;
public class InvoiceAttributeFound implements Event{

	private List<InvoiceAttribute> invoiceAttributes;

	public InvoiceAttributeFound(List<InvoiceAttribute> invoiceAttributes) {
		this.invoiceAttributes = invoiceAttributes;
	}

	public List<InvoiceAttribute> getInvoiceAttributes()	{
		return invoiceAttributes;
	}

}
