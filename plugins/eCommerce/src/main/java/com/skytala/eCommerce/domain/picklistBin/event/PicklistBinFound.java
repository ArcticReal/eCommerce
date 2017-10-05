package com.skytala.eCommerce.domain.picklistBin.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.picklistBin.model.PicklistBin;
public class PicklistBinFound implements Event{

	private List<PicklistBin> picklistBins;

	public PicklistBinFound(List<PicklistBin> picklistBins) {
		this.picklistBins = picklistBins;
	}

	public List<PicklistBin> getPicklistBins()	{
		return picklistBins;
	}

}
