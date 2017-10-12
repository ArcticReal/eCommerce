package com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.model.GlBudgetXref;
public class GlBudgetXrefAdded implements Event{

	private GlBudgetXref addedGlBudgetXref;
	private boolean success;

	public GlBudgetXrefAdded(GlBudgetXref addedGlBudgetXref, boolean success){
		this.addedGlBudgetXref = addedGlBudgetXref;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlBudgetXref getAddedGlBudgetXref() {
		return addedGlBudgetXref;
	}

}
