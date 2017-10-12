package com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.model.InvoiceTypeAttr;
public class InvoiceTypeAttrFound implements Event{

	private List<InvoiceTypeAttr> invoiceTypeAttrs;

	public InvoiceTypeAttrFound(List<InvoiceTypeAttr> invoiceTypeAttrs) {
		this.invoiceTypeAttrs = invoiceTypeAttrs;
	}

	public List<InvoiceTypeAttr> getInvoiceTypeAttrs()	{
		return invoiceTypeAttrs;
	}

}
