package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintMeter.model.FixedAssetMaintMeter;
public class FixedAssetMaintMeterAdded implements Event{

	private FixedAssetMaintMeter addedFixedAssetMaintMeter;
	private boolean success;

	public FixedAssetMaintMeterAdded(FixedAssetMaintMeter addedFixedAssetMaintMeter, boolean success){
		this.addedFixedAssetMaintMeter = addedFixedAssetMaintMeter;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetMaintMeter getAddedFixedAssetMaintMeter() {
		return addedFixedAssetMaintMeter;
	}

}
