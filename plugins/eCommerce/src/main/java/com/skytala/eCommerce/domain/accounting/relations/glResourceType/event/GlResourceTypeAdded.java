package com.skytala.eCommerce.domain.accounting.relations.glResourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;
public class GlResourceTypeAdded implements Event{

	private GlResourceType addedGlResourceType;
	private boolean success;

	public GlResourceTypeAdded(GlResourceType addedGlResourceType, boolean success){
		this.addedGlResourceType = addedGlResourceType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlResourceType getAddedGlResourceType() {
		return addedGlResourceType;
	}

}
