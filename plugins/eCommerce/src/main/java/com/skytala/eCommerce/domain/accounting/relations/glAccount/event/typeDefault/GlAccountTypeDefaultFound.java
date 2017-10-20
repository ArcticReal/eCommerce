package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault.GlAccountTypeDefault;
public class GlAccountTypeDefaultFound implements Event{

	private List<GlAccountTypeDefault> glAccountTypeDefaults;

	public GlAccountTypeDefaultFound(List<GlAccountTypeDefault> glAccountTypeDefaults) {
		this.glAccountTypeDefaults = glAccountTypeDefaults;
	}

	public List<GlAccountTypeDefault> getGlAccountTypeDefaults()	{
		return glAccountTypeDefaults;
	}

}
