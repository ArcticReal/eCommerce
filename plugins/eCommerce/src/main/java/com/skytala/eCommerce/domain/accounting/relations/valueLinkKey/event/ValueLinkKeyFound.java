package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;
public class ValueLinkKeyFound implements Event{

	private List<ValueLinkKey> valueLinkKeys;

	public ValueLinkKeyFound(List<ValueLinkKey> valueLinkKeys) {
		this.valueLinkKeys = valueLinkKeys;
	}

	public List<ValueLinkKey> getValueLinkKeys()	{
		return valueLinkKeys;
	}

}
