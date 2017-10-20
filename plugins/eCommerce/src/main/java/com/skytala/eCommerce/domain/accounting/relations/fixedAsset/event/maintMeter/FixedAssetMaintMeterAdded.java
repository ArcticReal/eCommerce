package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintMeter.FixedAssetMaintMeter;
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
