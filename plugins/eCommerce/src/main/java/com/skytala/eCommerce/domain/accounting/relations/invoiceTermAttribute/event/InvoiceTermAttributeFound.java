package com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.model.InvoiceTermAttribute;
public class InvoiceTermAttributeFound implements Event{

	private List<InvoiceTermAttribute> invoiceTermAttributes;

	public InvoiceTermAttributeFound(List<InvoiceTermAttribute> invoiceTermAttributes) {
		this.invoiceTermAttributes = invoiceTermAttributes;
	}

	public List<InvoiceTermAttribute> getInvoiceTermAttributes()	{
		return invoiceTermAttributes;
	}

}
