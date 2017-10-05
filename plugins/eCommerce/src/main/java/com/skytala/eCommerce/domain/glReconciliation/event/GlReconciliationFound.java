package com.skytala.eCommerce.domain.glReconciliation.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glReconciliation.model.GlReconciliation;
public class GlReconciliationFound implements Event{

	private List<GlReconciliation> glReconciliations;

	public GlReconciliationFound(List<GlReconciliation> glReconciliations) {
		this.glReconciliations = glReconciliations;
	}

	public List<GlReconciliation> getGlReconciliations()	{
		return glReconciliations;
	}

}
