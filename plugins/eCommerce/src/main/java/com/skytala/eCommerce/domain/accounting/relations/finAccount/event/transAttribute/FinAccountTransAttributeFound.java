package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute.FinAccountTransAttribute;
public class FinAccountTransAttributeFound implements Event{

	private List<FinAccountTransAttribute> finAccountTransAttributes;

	public FinAccountTransAttributeFound(List<FinAccountTransAttribute> finAccountTransAttributes) {
		this.finAccountTransAttributes = finAccountTransAttributes;
	}

	public List<FinAccountTransAttribute> getFinAccountTransAttributes()	{
		return finAccountTransAttributes;
	}

}
