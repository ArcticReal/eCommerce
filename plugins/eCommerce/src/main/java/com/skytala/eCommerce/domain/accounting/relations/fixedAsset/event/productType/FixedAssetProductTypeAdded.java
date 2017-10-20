package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.productType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.productType.FixedAssetProductType;
public class FixedAssetProductTypeAdded implements Event{

	private FixedAssetProductType addedFixedAssetProductType;
	private boolean success;

	public FixedAssetProductTypeAdded(FixedAssetProductType addedFixedAssetProductType, boolean success){
		this.addedFixedAssetProductType = addedFixedAssetProductType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetProductType getAddedFixedAssetProductType() {
		return addedFixedAssetProductType;
	}

}
