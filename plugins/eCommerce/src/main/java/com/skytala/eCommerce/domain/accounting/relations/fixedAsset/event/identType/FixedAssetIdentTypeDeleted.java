package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.identType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.identType.FixedAssetIdentType;
public class FixedAssetIdentTypeDeleted implements Event{

	private boolean success;

	public FixedAssetIdentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
