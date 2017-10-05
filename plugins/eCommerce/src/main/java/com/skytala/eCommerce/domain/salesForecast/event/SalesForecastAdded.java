package com.skytala.eCommerce.domain.salesForecast.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.salesForecast.model.SalesForecast;
public class SalesForecastAdded implements Event{

	private SalesForecast addedSalesForecast;
	private boolean success;

	public SalesForecastAdded(SalesForecast addedSalesForecast, boolean success){
		this.addedSalesForecast = addedSalesForecast;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesForecast getAddedSalesForecast() {
		return addedSalesForecast;
	}

}
