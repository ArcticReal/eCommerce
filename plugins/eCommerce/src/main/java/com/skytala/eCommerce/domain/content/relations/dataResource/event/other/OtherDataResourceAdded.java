package com.skytala.eCommerce.domain.content.relations.dataResource.event.other;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.other.OtherDataResource;
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
