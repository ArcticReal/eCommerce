package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.model.FixedAssetTypeAttr;
public class FixedAssetTypeAttrDeleted implements Event{

	private boolean success;

	public FixedAssetTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
