package com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.model.FixedAssetAttribute;
public class FixedAssetAttributeUpdated implements Event{

	private boolean success;

	public FixedAssetAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
