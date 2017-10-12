package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.model.FixedAssetStdCostType;
public class FixedAssetStdCostTypeAdded implements Event{

	private FixedAssetStdCostType addedFixedAssetStdCostType;
	private boolean success;

	public FixedAssetStdCostTypeAdded(FixedAssetStdCostType addedFixedAssetStdCostType, boolean success){
		this.addedFixedAssetStdCostType = addedFixedAssetStdCostType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetStdCostType getAddedFixedAssetStdCostType() {
		return addedFixedAssetStdCostType;
	}

}
