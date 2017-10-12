package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.model.FixedAssetMeter;
public class FixedAssetMeterUpdated implements Event{

	private boolean success;

	public FixedAssetMeterUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
