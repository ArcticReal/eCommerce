package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;
public class InvoiceItemTypeMapAdded implements Event{

	private InvoiceItemTypeMap addedInvoiceItemTypeMap;
	private boolean success;

	public InvoiceItemTypeMapAdded(InvoiceItemTypeMap addedInvoiceItemTypeMap, boolean success){
		this.addedInvoiceItemTypeMap = addedInvoiceItemTypeMap;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItemTypeMap getAddedInvoiceItemTypeMap() {
		return addedInvoiceItemTypeMap;
	}

}
