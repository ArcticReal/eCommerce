package com.skytala.eCommerce.domain.party.relations.termTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.termTypeAttr.model.TermTypeAttr;
public class TermTypeAttrDeleted implements Event{

	private boolean success;

	public TermTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
