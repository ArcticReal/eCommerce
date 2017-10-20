package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history.GlAccountHistory;
public class GlAccountHistoryAdded implements Event{

	private GlAccountHistory addedGlAccountHistory;
	private boolean success;

	public GlAccountHistoryAdded(GlAccountHistory addedGlAccountHistory, boolean success){
		this.addedGlAccountHistory = addedGlAccountHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountHistory getAddedGlAccountHistory() {
		return addedGlAccountHistory;
	}

}
