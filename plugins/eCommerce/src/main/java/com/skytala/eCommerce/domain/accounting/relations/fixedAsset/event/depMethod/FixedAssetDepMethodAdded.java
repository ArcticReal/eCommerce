package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod.FixedAssetDepMethod;
public class FixedAssetDepMethodAdded implements Event{

	private FixedAssetDepMethod addedFixedAssetDepMethod;
	private boolean success;

	public FixedAssetDepMethodAdded(FixedAssetDepMethod addedFixedAssetDepMethod, boolean success){
		this.addedFixedAssetDepMethod = addedFixedAssetDepMethod;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetDepMethod getAddedFixedAssetDepMethod() {
		return addedFixedAssetDepMethod;
	}

}
