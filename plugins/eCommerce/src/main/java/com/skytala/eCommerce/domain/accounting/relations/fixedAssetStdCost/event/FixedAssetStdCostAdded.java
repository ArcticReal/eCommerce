package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;
public class FixedAssetStdCostAdded implements Event{

	private FixedAssetStdCost addedFixedAssetStdCost;
	private boolean success;

	public FixedAssetStdCostAdded(FixedAssetStdCost addedFixedAssetStdCost, boolean success){
		this.addedFixedAssetStdCost = addedFixedAssetStdCost;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetStdCost getAddedFixedAssetStdCost() {
		return addedFixedAssetStdCost;
	}

}
