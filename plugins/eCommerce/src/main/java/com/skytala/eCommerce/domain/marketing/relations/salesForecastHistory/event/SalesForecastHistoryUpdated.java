package com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.model.SalesForecastHistory;
public class SalesForecastHistoryUpdated implements Event{

	private boolean success;

	public SalesForecastHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
