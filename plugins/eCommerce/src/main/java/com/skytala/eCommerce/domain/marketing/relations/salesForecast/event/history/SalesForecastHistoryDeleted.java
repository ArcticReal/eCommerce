package com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.history.SalesForecastHistory;
public class SalesForecastHistoryDeleted implements Event{

	private boolean success;

	public SalesForecastHistoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
