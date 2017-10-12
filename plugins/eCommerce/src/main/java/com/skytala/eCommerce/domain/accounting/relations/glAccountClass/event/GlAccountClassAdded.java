package com.skytala.eCommerce.domain.accounting.relations.glAccountClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountClass.model.GlAccountClass;
public class GlAccountClassAdded implements Event{

	private GlAccountClass addedGlAccountClass;
	private boolean success;

	public GlAccountClassAdded(GlAccountClass addedGlAccountClass, boolean success){
		this.addedGlAccountClass = addedGlAccountClass;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountClass getAddedGlAccountClass() {
		return addedGlAccountClass;
	}

}
