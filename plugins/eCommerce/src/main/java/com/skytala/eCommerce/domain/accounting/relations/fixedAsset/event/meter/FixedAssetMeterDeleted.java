package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.meter.FixedAssetMeter;
public class FixedAssetMeterDeleted implements Event{

	private boolean success;

	public FixedAssetMeterDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
