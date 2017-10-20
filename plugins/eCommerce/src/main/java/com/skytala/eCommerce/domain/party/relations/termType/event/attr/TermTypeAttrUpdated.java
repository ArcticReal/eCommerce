package com.skytala.eCommerce.domain.party.relations.termType.event.attr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.termType.model.attr.TermTypeAttr;
public class TermTypeAttrUpdated implements Event{

	private boolean success;

	public TermTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
