package com.skytala.eCommerce.domain.invoiceItemType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceItemType.model.InvoiceItemType;
public class InvoiceItemTypeFound implements Event{

	private List<InvoiceItemType> invoiceItemTypes;

	public InvoiceItemTypeFound(List<InvoiceItemType> invoiceItemTypes) {
		this.invoiceItemTypes = invoiceItemTypes;
	}

	public List<InvoiceItemType> getInvoiceItemTypes()	{
		return invoiceItemTypes;
	}

}
