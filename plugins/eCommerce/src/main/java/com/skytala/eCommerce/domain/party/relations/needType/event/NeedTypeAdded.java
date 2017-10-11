package com.skytala.eCommerce.domain.party.relations.needType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.needType.model.NeedType;
public class NeedTypeAdded implements Event{

	private NeedType addedNeedType;
	private boolean success;

	public NeedTypeAdded(NeedType addedNeedType, boolean success){
		this.addedNeedType = addedNeedType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public NeedType getAddedNeedType() {
		return addedNeedType;
	}

}
