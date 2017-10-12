package com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.model.FinAccountTransType;
public class FinAccountTransTypeFound implements Event{

	private List<FinAccountTransType> finAccountTransTypes;

	public FinAccountTransTypeFound(List<FinAccountTransType> finAccountTransTypes) {
		this.finAccountTransTypes = finAccountTransTypes;
	}

	public List<FinAccountTransType> getFinAccountTransTypes()	{
		return finAccountTransTypes;
	}

}
