package com.skytala.eCommerce.domain.accounting.relations.invoice.event.status;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.status.InvoiceStatus;
public class InvoiceStatusFound implements Event{

	private List<InvoiceStatus> invoiceStatuss;

	public InvoiceStatusFound(List<InvoiceStatus> invoiceStatuss) {
		this.invoiceStatuss = invoiceStatuss;
	}

	public List<InvoiceStatus> getInvoiceStatuss()	{
		return invoiceStatuss;
	}

}
