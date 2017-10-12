package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.model.FixedAssetMaintMeter;
public class FixedAssetMaintMeterDeleted implements Event{

	private boolean success;

	public FixedAssetMaintMeterDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
