package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.model.FixedAssetProductType;
public class FixedAssetProductTypeUpdated implements Event{

	private boolean success;

	public FixedAssetProductTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}