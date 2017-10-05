package com.skytala.eCommerce.domain.finAccountType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccountType.model.FinAccountType;
public class FinAccountTypeFound implements Event{

	private List<FinAccountType> finAccountTypes;

	public FinAccountTypeFound(List<FinAccountType> finAccountTypes) {
		this.finAccountTypes = finAccountTypes;
	}

	public List<FinAccountType> getFinAccountTypes()	{
		return finAccountTypes;
	}

}
