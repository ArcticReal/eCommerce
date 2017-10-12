package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupType.model.GlAccountGroupType;
public class GlAccountGroupTypeAdded implements Event{

	private GlAccountGroupType addedGlAccountGroupType;
	private boolean success;

	public GlAccountGroupTypeAdded(GlAccountGroupType addedGlAccountGroupType, boolean success){
		this.addedGlAccountGroupType = addedGlAccountGroupType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountGroupType getAddedGlAccountGroupType() {
		return addedGlAccountGroupType;
	}

}
