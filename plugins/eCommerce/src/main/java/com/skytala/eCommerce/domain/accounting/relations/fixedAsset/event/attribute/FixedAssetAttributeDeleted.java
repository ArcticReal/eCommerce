package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.attribute.FixedAssetAttribute;
public class FixedAssetAttributeDeleted implements Event{

	private boolean success;

	public FixedAssetAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
