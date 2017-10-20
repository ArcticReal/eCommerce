package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.entry.GlReconciliationEntry;
public class GlReconciliationEntryAdded implements Event{

	private GlReconciliationEntry addedGlReconciliationEntry;
	private boolean success;

	public GlReconciliationEntryAdded(GlReconciliationEntry addedGlReconciliationEntry, boolean success){
		this.addedGlReconciliationEntry = addedGlReconciliationEntry;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlReconciliationEntry getAddedGlReconciliationEntry() {
		return addedGlReconciliationEntry;
	}

}
