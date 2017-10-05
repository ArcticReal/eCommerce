package com.skytala.eCommerce.domain.commContentAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.commContentAssocType.model.CommContentAssocType;
public class CommContentAssocTypeAdded implements Event{

	private CommContentAssocType addedCommContentAssocType;
	private boolean success;

	public CommContentAssocTypeAdded(CommContentAssocType addedCommContentAssocType, boolean success){
		this.addedCommContentAssocType = addedCommContentAssocType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommContentAssocType getAddedCommContentAssocType() {
		return addedCommContentAssocType;
	}

}
