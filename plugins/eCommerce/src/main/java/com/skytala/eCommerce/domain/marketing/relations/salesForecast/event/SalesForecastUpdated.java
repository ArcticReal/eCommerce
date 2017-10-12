package com.skytala.eCommerce.domain.marketing.relations.salesForecast.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.SalesForecast;
public class SalesForecastUpdated implements Event{

	private boolean success;

	public SalesForecastUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
