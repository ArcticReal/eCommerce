package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model.FinAccountStatus;
public class FinAccountStatusFound implements Event{

	private List<FinAccountStatus> finAccountStatuss;

	public FinAccountStatusFound(List<FinAccountStatus> finAccountStatuss) {
		this.finAccountStatuss = finAccountStatuss;
	}

	public List<FinAccountStatus> getFinAccountStatuss()	{
		return finAccountStatuss;
	}

}
