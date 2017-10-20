package com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.history.SalesForecastHistory;
public class SalesForecastHistoryUpdated implements Event{

	private boolean success;

	public SalesForecastHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
