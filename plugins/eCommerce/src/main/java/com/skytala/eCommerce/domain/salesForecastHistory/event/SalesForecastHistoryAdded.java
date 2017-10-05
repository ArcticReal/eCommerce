package com.skytala.eCommerce.domain.salesForecastHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.salesForecastHistory.model.SalesForecastHistory;
public class SalesForecastHistoryAdded implements Event{

	private SalesForecastHistory addedSalesForecastHistory;
	private boolean success;

	public SalesForecastHistoryAdded(SalesForecastHistory addedSalesForecastHistory, boolean success){
		this.addedSalesForecastHistory = addedSalesForecastHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesForecastHistory getAddedSalesForecastHistory() {
		return addedSalesForecastHistory;
	}

}
