package com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdentType.model.FixedAssetIdentType;
public class FixedAssetIdentTypeDeleted implements Event{

	private boolean success;

	public FixedAssetIdentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}