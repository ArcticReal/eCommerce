package com.skytala.eCommerce.domain.accounting.relations.glAccountHistory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountHistory.model.GlAccountHistory;
public class GlAccountHistoryFound implements Event{

	private List<GlAccountHistory> glAccountHistorys;

	public GlAccountHistoryFound(List<GlAccountHistory> glAccountHistorys) {
		this.glAccountHistorys = glAccountHistorys;
	}

	public List<GlAccountHistory> getGlAccountHistorys()	{
		return glAccountHistorys;
	}

}
