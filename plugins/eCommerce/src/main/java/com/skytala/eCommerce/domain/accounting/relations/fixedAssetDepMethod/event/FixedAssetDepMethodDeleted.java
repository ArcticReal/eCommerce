package com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.model.FixedAssetDepMethod;
public class FixedAssetDepMethodDeleted implements Event{

	private boolean success;

	public FixedAssetDepMethodDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
