package com.skytala.eCommerce.domain.glAccountGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountGroup.model.GlAccountGroup;
public class GlAccountGroupFound implements Event{

	private List<GlAccountGroup> glAccountGroups;

	public GlAccountGroupFound(List<GlAccountGroup> glAccountGroups) {
		this.glAccountGroups = glAccountGroups;
	}

	public List<GlAccountGroup> getGlAccountGroups()	{
		return glAccountGroups;
	}

}
