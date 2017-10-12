package com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.model.SalesForecastDetail;
public class SalesForecastDetailDeleted implements Event{

	private boolean success;

	public SalesForecastDetailDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
