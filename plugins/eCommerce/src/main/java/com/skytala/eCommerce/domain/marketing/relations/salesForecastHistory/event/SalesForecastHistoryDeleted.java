package com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.model.SalesForecastHistory;
public class SalesForecastHistoryDeleted implements Event{

	private boolean success;

	public SalesForecastHistoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
