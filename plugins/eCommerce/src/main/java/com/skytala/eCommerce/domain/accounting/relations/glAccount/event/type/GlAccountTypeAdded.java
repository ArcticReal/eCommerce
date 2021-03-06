package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;
public class GlAccountTypeAdded implements Event{

	private GlAccountType addedGlAccountType;
	private boolean success;

	public GlAccountTypeAdded(GlAccountType addedGlAccountType, boolean success){
		this.addedGlAccountType = addedGlAccountType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountType getAddedGlAccountType() {
		return addedGlAccountType;
	}

}
