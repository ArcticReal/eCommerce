package com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.glXref.GlBudgetXref;
public class GlBudgetXrefFound implements Event{

	private List<GlBudgetXref> glBudgetXrefs;

	public GlBudgetXrefFound(List<GlBudgetXref> glBudgetXrefs) {
		this.glBudgetXrefs = glBudgetXrefs;
	}

	public List<GlBudgetXref> getGlBudgetXrefs()	{
		return glBudgetXrefs;
	}

}
