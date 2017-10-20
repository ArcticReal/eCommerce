package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCost;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.stdCost.FixedAssetStdCost;
public class FixedAssetStdCostFound implements Event{

	private List<FixedAssetStdCost> fixedAssetStdCosts;

	public FixedAssetStdCostFound(List<FixedAssetStdCost> fixedAssetStdCosts) {
		this.fixedAssetStdCosts = fixedAssetStdCosts;
	}

	public List<FixedAssetStdCost> getFixedAssetStdCosts()	{
		return fixedAssetStdCosts;
	}

}
