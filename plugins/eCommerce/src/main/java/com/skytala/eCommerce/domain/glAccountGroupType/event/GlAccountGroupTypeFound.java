package com.skytala.eCommerce.domain.glAccountGroupType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountGroupType.model.GlAccountGroupType;
public class GlAccountGroupTypeFound implements Event{

	private List<GlAccountGroupType> glAccountGroupTypes;

	public GlAccountGroupTypeFound(List<GlAccountGroupType> glAccountGroupTypes) {
		this.glAccountGroupTypes = glAccountGroupTypes;
	}

	public List<GlAccountGroupType> getGlAccountGroupTypes()	{
		return glAccountGroupTypes;
	}

}
