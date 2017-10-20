package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.meter.FixedAssetMeter;
public class FixedAssetMeterAdded implements Event{

	private FixedAssetMeter addedFixedAssetMeter;
	private boolean success;

	public FixedAssetMeterAdded(FixedAssetMeter addedFixedAssetMeter, boolean success){
		this.addedFixedAssetMeter = addedFixedAssetMeter;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetMeter getAddedFixedAssetMeter() {
		return addedFixedAssetMeter;
	}

}
