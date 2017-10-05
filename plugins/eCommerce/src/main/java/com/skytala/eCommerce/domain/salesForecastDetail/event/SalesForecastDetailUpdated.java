package com.skytala.eCommerce.domain.salesForecastDetail.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.salesForecastDetail.model.SalesForecastDetail;
public class SalesForecastDetailUpdated implements Event{

	private boolean success;

	public SalesForecastDetailUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
