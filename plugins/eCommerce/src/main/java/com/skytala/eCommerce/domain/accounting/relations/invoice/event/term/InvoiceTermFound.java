package com.skytala.eCommerce.domain.accounting.relations.invoice.event.term;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.term.InvoiceTerm;
public class InvoiceTermFound implements Event{

	private List<InvoiceTerm> invoiceTerms;

	public InvoiceTermFound(List<InvoiceTerm> invoiceTerms) {
		this.invoiceTerms = invoiceTerms;
	}

	public List<InvoiceTerm> getInvoiceTerms()	{
		return invoiceTerms;
	}

}
