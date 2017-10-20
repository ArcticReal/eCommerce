package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintMeter.FixedAssetMaintMeter;
public class FixedAssetMaintMeterUpdated implements Event{

	private boolean success;

	public FixedAssetMaintMeterUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}