package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.model.FixedAssetMeter;
public class FixedAssetMeterFound implements Event{

	private List<FixedAssetMeter> fixedAssetMeters;

	public FixedAssetMeterFound(List<FixedAssetMeter> fixedAssetMeters) {
		this.fixedAssetMeters = fixedAssetMeters;
	}

	public List<FixedAssetMeter> getFixedAssetMeters()	{
		return fixedAssetMeters;
	}

}
