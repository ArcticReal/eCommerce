package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.type.FinAccountType;
public class FinAccountTypeFound implements Event{

	private List<FinAccountType> finAccountTypes;

	public FinAccountTypeFound(List<FinAccountType> finAccountTypes) {
		this.finAccountTypes = finAccountTypes;
	}

	public List<FinAccountType> getFinAccountTypes()	{
		return finAccountTypes;
	}

}
