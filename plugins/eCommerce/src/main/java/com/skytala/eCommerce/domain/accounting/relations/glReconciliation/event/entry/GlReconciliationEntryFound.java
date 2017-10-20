package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.entry.GlReconciliationEntry;
public class GlReconciliationEntryFound implements Event{

	private List<GlReconciliationEntry> glReconciliationEntrys;

	public GlReconciliationEntryFound(List<GlReconciliationEntry> glReconciliationEntrys) {
		this.glReconciliationEntrys = glReconciliationEntrys;
	}

	public List<GlReconciliationEntry> getGlReconciliationEntrys()	{
		return glReconciliationEntrys;
	}

}
