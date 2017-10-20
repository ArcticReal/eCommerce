package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.productType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.productType.FixedAssetProductType;
public class FixedAssetProductTypeDeleted implements Event{

	private boolean success;

	public FixedAssetProductTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
