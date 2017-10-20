package com.skytala.eCommerce.domain.accounting.relations.invoice.event.termAttribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.termAttribute.InvoiceTermAttribute;
public class InvoiceTermAttributeFound implements Event{

	private List<InvoiceTermAttribute> invoiceTermAttributes;

	public InvoiceTermAttributeFound(List<InvoiceTermAttribute> invoiceTermAttributes) {
		this.invoiceTermAttributes = invoiceTermAttributes;
	}

	public List<InvoiceTermAttribute> getInvoiceTermAttributes()	{
		return invoiceTermAttributes;
	}

}
