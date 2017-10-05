package com.skytala.eCommerce.domain.glResourceType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glResourceType.model.GlResourceType;
public class GlResourceTypeFound implements Event{

	private List<GlResourceType> glResourceTypes;

	public GlResourceTypeFound(List<GlResourceType> glResourceTypes) {
		this.glResourceTypes = glResourceTypes;
	}

	public List<GlResourceType> getGlResourceTypes()	{
		return glResourceTypes;
	}

}
