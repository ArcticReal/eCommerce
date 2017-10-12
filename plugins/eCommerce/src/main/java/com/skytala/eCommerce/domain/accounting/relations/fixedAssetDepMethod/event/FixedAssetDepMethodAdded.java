package com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.model.FixedAssetDepMethod;
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
