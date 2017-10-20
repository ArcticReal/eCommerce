package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.product.FixedAssetProduct;
public class FixedAssetProductUpdated implements Event{

	private boolean success;

	public FixedAssetProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
