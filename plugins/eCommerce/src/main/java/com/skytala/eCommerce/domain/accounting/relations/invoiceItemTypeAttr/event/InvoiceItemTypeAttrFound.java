package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.model.InvoiceItemTypeAttr;
public class InvoiceItemTypeAttrFound implements Event{

	private List<InvoiceItemTypeAttr> invoiceItemTypeAttrs;

	public InvoiceItemTypeAttrFound(List<InvoiceItemTypeAttr> invoiceItemTypeAttrs) {
		this.invoiceItemTypeAttrs = invoiceItemTypeAttrs;
	}

	public List<InvoiceItemTypeAttr> getInvoiceItemTypeAttrs()	{
		return invoiceItemTypeAttrs;
	}

}
