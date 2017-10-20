package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.meter;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.meter.FixedAssetMeter;
public class FixedAssetMeterFound implements Event{

	private List<FixedAssetMeter> fixedAssetMeters;

	public FixedAssetMeterFound(List<FixedAssetMeter> fixedAssetMeters) {
		this.fixedAssetMeters = fixedAssetMeters;
	}

	public List<FixedAssetMeter> getFixedAssetMeters()	{
		return fixedAssetMeters;
	}

}
