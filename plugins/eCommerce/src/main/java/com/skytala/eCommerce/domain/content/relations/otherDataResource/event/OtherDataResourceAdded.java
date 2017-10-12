package com.skytala.eCommerce.domain.content.relations.otherDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.otherDataResource.model.OtherDataResource;
public class OtherDataResourceAdded implements Event{

	private OtherDataResource addedOtherDataResource;
	private boolean success;

	public OtherDataResourceAdded(OtherDataResource addedOtherDataResource, boolean success){
		this.addedOtherDataResource = addedOtherDataResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OtherDataResource getAddedOtherDataResource() {
		return addedOtherDataResource;
	}

}
