package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeGlAccount.InvoiceItemTypeGlAccount;
public class InvoiceItemTypeGlAccountFound implements Event{

	private List<InvoiceItemTypeGlAccount> invoiceItemTypeGlAccounts;

	public InvoiceItemTypeGlAccountFound(List<InvoiceItemTypeGlAccount> invoiceItemTypeGlAccounts) {
		this.invoiceItemTypeGlAccounts = invoiceItemTypeGlAccounts;
	}

	public List<InvoiceItemTypeGlAccount> getInvoiceItemTypeGlAccounts()	{
		return invoiceItemTypeGlAccounts;
	}

}
