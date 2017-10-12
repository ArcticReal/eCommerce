package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.model.InvoiceItemTypeMap;
public class InvoiceItemTypeMapFound implements Event{

	private List<InvoiceItemTypeMap> invoiceItemTypeMaps;

	public InvoiceItemTypeMapFound(List<InvoiceItemTypeMap> invoiceItemTypeMaps) {
		this.invoiceItemTypeMaps = invoiceItemTypeMaps;
	}

	public List<InvoiceItemTypeMap> getInvoiceItemTypeMaps()	{
		return invoiceItemTypeMaps;
	}

}
