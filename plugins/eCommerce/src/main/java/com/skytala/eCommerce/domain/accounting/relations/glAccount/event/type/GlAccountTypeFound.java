package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;
public class GlAccountTypeFound implements Event{

	private List<GlAccountType> glAccountTypes;

	public GlAccountTypeFound(List<GlAccountType> glAccountTypes) {
		this.glAccountTypes = glAccountTypes;
	}

	public List<GlAccountType> getGlAccountTypes()	{
		return glAccountTypes;
	}

}
