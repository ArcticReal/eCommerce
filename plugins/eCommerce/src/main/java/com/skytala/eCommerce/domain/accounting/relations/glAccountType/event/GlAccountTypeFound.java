package com.skytala.eCommerce.domain.accounting.relations.glAccountType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountType.model.GlAccountType;
public class GlAccountTypeFound implements Event{

	private List<GlAccountType> glAccountTypes;

	public GlAccountTypeFound(List<GlAccountType> glAccountTypes) {
		this.glAccountTypes = glAccountTypes;
	}

	public List<GlAccountType> getGlAccountTypes()	{
		return glAccountTypes;
	}

}
