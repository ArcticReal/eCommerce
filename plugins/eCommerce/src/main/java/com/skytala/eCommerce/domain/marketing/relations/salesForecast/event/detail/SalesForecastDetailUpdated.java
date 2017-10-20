package com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.detail;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.detail.SalesForecastDetail;
public class SalesForecastDetailUpdated implements Event{

	private boolean success;

	public SalesForecastDetailUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
