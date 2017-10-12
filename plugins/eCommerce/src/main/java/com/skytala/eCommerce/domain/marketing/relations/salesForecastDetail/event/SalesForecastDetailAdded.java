package com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.model.SalesForecastDetail;
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
