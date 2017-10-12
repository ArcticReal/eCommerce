package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.model.InvoiceItemTypeGlAccount;
public class InvoiceItemTypeGlAccountFound implements Event{

	private List<InvoiceItemTypeGlAccount> invoiceItemTypeGlAccounts;

	public InvoiceItemTypeGlAccountFound(List<InvoiceItemTypeGlAccount> invoiceItemTypeGlAccounts) {
		this.invoiceItemTypeGlAccounts = invoiceItemTypeGlAccounts;
	}

	public List<InvoiceItemTypeGlAccount> getInvoiceItemTypeGlAccounts()	{
		return invoiceItemTypeGlAccounts;
	}

}
