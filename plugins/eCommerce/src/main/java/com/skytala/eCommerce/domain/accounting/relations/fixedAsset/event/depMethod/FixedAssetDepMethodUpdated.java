package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod.FixedAssetDepMethod;
public class FixedAssetDepMethodUpdated implements Event{

	private boolean success;

	public FixedAssetDepMethodUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
