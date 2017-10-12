package com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.model.GlAccountTypeDefault;
public class GlAccountTypeDefaultAdded implements Event{

	private GlAccountTypeDefault addedGlAccountTypeDefault;
	private boolean success;

	public GlAccountTypeDefaultAdded(GlAccountTypeDefault addedGlAccountTypeDefault, boolean success){
		this.addedGlAccountTypeDefault = addedGlAccountTypeDefault;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountTypeDefault getAddedGlAccountTypeDefault() {
		return addedGlAccountTypeDefault;
	}

}
