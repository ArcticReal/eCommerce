package com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.model.GlReconciliationEntry;
public class GlReconciliationEntryFound implements Event{

	private List<GlReconciliationEntry> glReconciliationEntrys;

	public GlReconciliationEntryFound(List<GlReconciliationEntry> glReconciliationEntrys) {
		this.glReconciliationEntrys = glReconciliationEntrys;
	}

	public List<GlReconciliationEntry> getGlReconciliationEntrys()	{
		return glReconciliationEntrys;
	}

}
