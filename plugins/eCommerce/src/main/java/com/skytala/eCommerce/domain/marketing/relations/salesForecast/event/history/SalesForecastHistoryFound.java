package com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.history.SalesForecastHistory;
public class SalesForecastHistoryFound implements Event{

	private List<SalesForecastHistory> salesForecastHistorys;

	public SalesForecastHistoryFound(List<SalesForecastHistory> salesForecastHistorys) {
		this.salesForecastHistorys = salesForecastHistorys;
	}

	public List<SalesForecastHistory> getSalesForecastHistorys()	{
		return salesForecastHistorys;
	}

}
