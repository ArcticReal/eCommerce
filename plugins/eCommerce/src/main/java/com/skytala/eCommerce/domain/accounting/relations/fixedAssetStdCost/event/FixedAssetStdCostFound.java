package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;
public class FixedAssetStdCostFound implements Event{

	private List<FixedAssetStdCost> fixedAssetStdCosts;

	public FixedAssetStdCostFound(List<FixedAssetStdCost> fixedAssetStdCosts) {
		this.fixedAssetStdCosts = fixedAssetStdCosts;
	}

	public List<FixedAssetStdCost> getFixedAssetStdCosts()	{
		return fixedAssetStdCosts;
	}

}
