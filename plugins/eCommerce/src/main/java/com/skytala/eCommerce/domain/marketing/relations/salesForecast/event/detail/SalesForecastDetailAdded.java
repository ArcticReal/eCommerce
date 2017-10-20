package com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.detail;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.detail.SalesForecastDetail;
public class SalesForecastDetailAdded implements Event{

	private SalesForecastDetail addedSalesForecastDetail;
	private boolean success;

	public SalesForecastDetailAdded(SalesForecastDetail addedSalesForecastDetail, boolean success){
		this.addedSalesForecastDetail = addedSalesForecastDetail;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesForecastDetail getAddedSalesForecastDetail() {
		return addedSalesForecastDetail;
	}

}
