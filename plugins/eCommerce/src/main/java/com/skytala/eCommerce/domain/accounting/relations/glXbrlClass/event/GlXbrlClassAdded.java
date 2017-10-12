package com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.model.GlXbrlClass;
public class GlXbrlClassAdded implements Event{

	private GlXbrlClass addedGlXbrlClass;
	private boolean success;

	public GlXbrlClassAdded(GlXbrlClass addedGlXbrlClass, boolean success){
		this.addedGlXbrlClass = addedGlXbrlClass;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlXbrlClass getAddedGlXbrlClass() {
		return addedGlXbrlClass;
	}

}
