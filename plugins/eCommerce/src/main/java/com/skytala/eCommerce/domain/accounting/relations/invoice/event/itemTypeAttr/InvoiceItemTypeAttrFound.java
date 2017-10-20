package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeAttr.InvoiceItemTypeAttr;
public class InvoiceItemTypeAttrFound implements Event{

	private List<InvoiceItemTypeAttr> invoiceItemTypeAttrs;

	public InvoiceItemTypeAttrFound(List<InvoiceItemTypeAttr> invoiceItemTypeAttrs) {
		this.invoiceItemTypeAttrs = invoiceItemTypeAttrs;
	}

	public List<InvoiceItemTypeAttr> getInvoiceItemTypeAttrs()	{
		return invoiceItemTypeAttrs;
	}

}
