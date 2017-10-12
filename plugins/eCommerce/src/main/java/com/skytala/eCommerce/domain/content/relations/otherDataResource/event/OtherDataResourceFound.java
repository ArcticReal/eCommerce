package com.skytala.eCommerce.domain.content.relations.otherDataResource.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.otherDataResource.model.OtherDataResource;
public class OtherDataResourceFound implements Event{

	private List<OtherDataResource> otherDataResources;

	public OtherDataResourceFound(List<OtherDataResource> otherDataResources) {
		this.otherDataResources = otherDataResources;
	}

	public List<OtherDataResource> getOtherDataResources()	{
		return otherDataResources;
	}

}
