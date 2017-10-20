package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintMeter;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintMeter.FixedAssetMaintMeter;
public class FixedAssetMaintMeterFound implements Event{

	private List<FixedAssetMaintMeter> fixedAssetMaintMeters;

	public FixedAssetMaintMeterFound(List<FixedAssetMaintMeter> fixedAssetMaintMeters) {
		this.fixedAssetMaintMeters = fixedAssetMaintMeters;
	}

	public List<FixedAssetMaintMeter> getFixedAssetMaintMeters()	{
		return fixedAssetMaintMeters;
	}

}
