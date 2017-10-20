package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupType.GlAccountGroupType;
public class GlAccountGroupTypeFound implements Event{

	private List<GlAccountGroupType> glAccountGroupTypes;

	public GlAccountGroupTypeFound(List<GlAccountGroupType> glAccountGroupTypes) {
		this.glAccountGroupTypes = glAccountGroupTypes;
	}

	public List<GlAccountGroupType> getGlAccountGroupTypes()	{
		return glAccountGroupTypes;
	}

}
