package com.skytala.eCommerce.domain.fixedAssetStdCostType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetStdCostType.model.FixedAssetStdCostType;
public class FixedAssetStdCostTypeFound implements Event{

	private List<FixedAssetStdCostType> fixedAssetStdCostTypes;

	public FixedAssetStdCostTypeFound(List<FixedAssetStdCostType> fixedAssetStdCostTypes) {
		this.fixedAssetStdCostTypes = fixedAssetStdCostTypes;
	}

	public List<FixedAssetStdCostType> getFixedAssetStdCostTypes()	{
		return fixedAssetStdCostTypes;
	}

}
