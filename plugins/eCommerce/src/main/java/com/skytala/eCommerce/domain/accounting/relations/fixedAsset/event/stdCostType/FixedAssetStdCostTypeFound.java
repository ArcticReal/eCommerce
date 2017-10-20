package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCostType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.stdCostType.FixedAssetStdCostType;
public class FixedAssetStdCostTypeFound implements Event{

	private List<FixedAssetStdCostType> fixedAssetStdCostTypes;

	public FixedAssetStdCostTypeFound(List<FixedAssetStdCostType> fixedAssetStdCostTypes) {
		this.fixedAssetStdCostTypes = fixedAssetStdCostTypes;
	}

	public List<FixedAssetStdCostType> getFixedAssetStdCostTypes()	{
		return fixedAssetStdCostTypes;
	}

}
