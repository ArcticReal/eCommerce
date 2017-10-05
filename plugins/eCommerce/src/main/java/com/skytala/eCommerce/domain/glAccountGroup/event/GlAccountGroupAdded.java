package com.skytala.eCommerce.domain.glAccountGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountGroup.model.GlAccountGroup;
public class GlAccountGroupAdded implements Event{

	private GlAccountGroup addedGlAccountGroup;
	private boolean success;

	public GlAccountGroupAdded(GlAccountGroup addedGlAccountGroup, boolean success){
		this.addedGlAccountGroup = addedGlAccountGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountGroup getAddedGlAccountGroup() {
		return addedGlAccountGroup;
	}

}
