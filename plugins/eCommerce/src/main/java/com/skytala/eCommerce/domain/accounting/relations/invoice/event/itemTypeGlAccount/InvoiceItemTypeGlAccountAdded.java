package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeGlAccount.InvoiceItemTypeGlAccount;
public class InvoiceItemTypeGlAccountAdded implements Event{

	private InvoiceItemTypeGlAccount addedInvoiceItemTypeGlAccount;
	private boolean success;

	public InvoiceItemTypeGlAccountAdded(InvoiceItemTypeGlAccount addedInvoiceItemTypeGlAccount, boolean success){
		this.addedInvoiceItemTypeGlAccount = addedInvoiceItemTypeGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItemTypeGlAccount getAddedInvoiceItemTypeGlAccount() {
		return addedInvoiceItemTypeGlAccount;
	}

}
