package com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.model.FinAccountAttribute;
public class FinAccountAttributeFound implements Event{

	private List<FinAccountAttribute> finAccountAttributes;

	public FinAccountAttributeFound(List<FinAccountAttribute> finAccountAttributes) {
		this.finAccountAttributes = finAccountAttributes;
	}

	public List<FinAccountAttribute> getFinAccountAttributes()	{
		return finAccountAttributes;
	}

}
