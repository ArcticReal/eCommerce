package com.skytala.eCommerce.domain.salesForecast.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.salesForecast.model.SalesForecast;
public class SalesForecastDeleted implements Event{

	private boolean success;

	public SalesForecastDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}