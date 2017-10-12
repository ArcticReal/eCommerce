package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAttribute.model.InvoiceItemAttribute;
public class InvoiceItemAttributeFound implements Event{

	private List<InvoiceItemAttribute> invoiceItemAttributes;

	public InvoiceItemAttributeFound(List<InvoiceItemAttribute> invoiceItemAttributes) {
		this.invoiceItemAttributes = invoiceItemAttributes;
	}

	public List<InvoiceItemAttribute> getInvoiceItemAttributes()	{
		return invoiceItemAttributes;
	}

}
