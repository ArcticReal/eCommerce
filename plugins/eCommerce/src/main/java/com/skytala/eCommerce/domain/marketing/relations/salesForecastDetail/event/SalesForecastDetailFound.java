package com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.model.SalesForecastDetail;
public class SalesForecastDetailFound implements Event{

	private List<SalesForecastDetail> salesForecastDetails;

	public SalesForecastDetailFound(List<SalesForecastDetail> salesForecastDetails) {
		this.salesForecastDetails = salesForecastDetails;
	}

	public List<SalesForecastDetail> getSalesForecastDetails()	{
		return salesForecastDetails;
	}

}
