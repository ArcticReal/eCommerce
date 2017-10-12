package com.skytala.eCommerce.domain.marketing.relations.salesForecast.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.SalesForecast;
public class SalesForecastFound implements Event{

	private List<SalesForecast> salesForecasts;

	public SalesForecastFound(List<SalesForecast> salesForecasts) {
		this.salesForecasts = salesForecasts;
	}

	public List<SalesForecast> getSalesForecasts()	{
		return salesForecasts;
	}

}
