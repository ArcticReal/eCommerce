package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.model.FixedAssetMaintMeter;
public class FixedAssetMaintMeterUpdated implements Event{

	private boolean success;

	public FixedAssetMaintMeterUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
