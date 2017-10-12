package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.model.FixedAssetProduct;
public class FixedAssetProductUpdated implements Event{

	private boolean success;

	public FixedAssetProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
