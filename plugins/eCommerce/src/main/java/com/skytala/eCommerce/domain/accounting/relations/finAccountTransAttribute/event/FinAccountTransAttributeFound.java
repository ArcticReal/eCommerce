package com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.model.FinAccountTransAttribute;
public class FinAccountTransAttributeFound implements Event{

	private List<FinAccountTransAttribute> finAccountTransAttributes;

	public FinAccountTransAttributeFound(List<FinAccountTransAttribute> finAccountTransAttributes) {
		this.finAccountTransAttributes = finAccountTransAttributes;
	}

	public List<FinAccountTransAttribute> getFinAccountTransAttributes()	{
		return finAccountTransAttributes;
	}

}
