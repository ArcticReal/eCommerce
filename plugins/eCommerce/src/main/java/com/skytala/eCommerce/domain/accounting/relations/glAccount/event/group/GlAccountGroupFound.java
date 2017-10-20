package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.group.GlAccountGroup;
public class GlAccountGroupFound implements Event{

	private List<GlAccountGroup> glAccountGroups;

	public GlAccountGroupFound(List<GlAccountGroup> glAccountGroups) {
		this.glAccountGroups = glAccountGroups;
	}

	public List<GlAccountGroup> getGlAccountGroups()	{
		return glAccountGroups;
	}

}
