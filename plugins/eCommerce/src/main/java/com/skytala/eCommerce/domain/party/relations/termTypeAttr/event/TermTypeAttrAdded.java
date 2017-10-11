package com.skytala.eCommerce.domain.party.relations.termTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.termTypeAttr.model.TermTypeAttr;
public class TermTypeAttrAdded implements Event{

	private TermTypeAttr addedTermTypeAttr;
	private boolean success;

	public TermTypeAttrAdded(TermTypeAttr addedTermTypeAttr, boolean success){
		this.addedTermTypeAttr = addedTermTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TermTypeAttr getAddedTermTypeAttr() {
		return addedTermTypeAttr;
	}

}
