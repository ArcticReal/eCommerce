package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.auth.FinAccountAuth;
public class FinAccountAuthFound implements Event{

	private List<FinAccountAuth> finAccountAuths;

	public FinAccountAuthFound(List<FinAccountAuth> finAccountAuths) {
		this.finAccountAuths = finAccountAuths;
	}

	public List<FinAccountAuth> getFinAccountAuths()	{
		return finAccountAuths;
	}

}
