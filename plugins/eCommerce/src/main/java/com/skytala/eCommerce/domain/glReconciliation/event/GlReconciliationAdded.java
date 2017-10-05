package com.skytala.eCommerce.domain.glReconciliation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glReconciliation.model.GlReconciliation;
public class GlReconciliationAdded implements Event{

	private GlReconciliation addedGlReconciliation;
	private boolean success;

	public GlReconciliationAdded(GlReconciliation addedGlReconciliation, boolean success){
		this.addedGlReconciliation = addedGlReconciliation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlReconciliation getAddedGlReconciliation() {
		return addedGlReconciliation;
	}

}
